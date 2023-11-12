use jni::objects::{JClass, JObject};
use jni::sys::{jdouble, jint};
use jni::JNIEnv;
use libgeomag::{DateTime, GeodeticLocation, MagneticField, ModelExt, IGRF, WMM};

use crate::impl_helper::JNIEnvHelper;

mod impl_ext;
mod impl_helper;

fn throw_illegal_decimal<'local>(env: &mut JNIEnv<'local>, decimal: f64) -> JObject<'local> {
    let msg = format!("{} is not within the valid period", decimal);
    env.throw_new("java/lang/IllegalArgumentException", msg)
        .unwrap();

    JObject::null()
}

fn new_magnetic_field<'local>(env: &mut JNIEnv<'local>, m: &MagneticField) -> JObject<'local> {
    match env.new_magnetic_field(&m) {
        Ok(obj) => obj,
        Err(err) => {
            env.throw_new("java/lang/IllegalArgumentException", err.to_string())
                .unwrap();

            JObject::null()
        }
    }
}

#[no_mangle]
pub unsafe extern "system" fn Java_dev_sanmer_geomag_Geomag_toDecimalYears(
    _env: JNIEnv,
    _class: JClass,
    year: jint,
    month: jint,
    day: jint,
    hour: jint,
    minute: jint,
    second: jint,
) -> jdouble {
    let dt = DateTime::new(year, month, day, hour, minute, second);
    dt.decimal
}

#[no_mangle]
pub unsafe extern "system" fn Java_dev_sanmer_geomag_Geomag_wmm<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass,
    longitude: jdouble,
    latitude: jdouble,
    altitude: jdouble,
    decimal: jdouble,
) -> JObject<'local> {
    let l = GeodeticLocation::new(longitude, latitude, altitude);
    let wmm = WMM::new(decimal);

    match wmm {
        None => throw_illegal_decimal(&mut env, decimal),
        Some(wmm) => {
            let m = wmm.single(l);
            new_magnetic_field(&mut env, &m)
        }
    }
}

#[no_mangle]
pub unsafe extern "system" fn Java_dev_sanmer_geomag_Geomag_igrf<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass,
    longitude: jdouble,
    latitude: jdouble,
    altitude: jdouble,
    decimal: jdouble,
) -> JObject<'local> {
    let l = GeodeticLocation::new(longitude, latitude, altitude);
    let igrf = IGRF::new(decimal);

    match igrf {
        None => throw_illegal_decimal(&mut env, decimal),
        Some(igrf) => {
            let m = igrf.single(l);
            new_magnetic_field(&mut env, &m)
        }
    }
}
