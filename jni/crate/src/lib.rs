use jni::objects::{JClass, JObject};
use jni::sys::{jdouble, jint};
use jni::JNIEnv;
use libgeomag::{DateTime, GeodeticLocation, Geomag, MagneticField};

use crate::impl_helper::JNIEnvHelper;

mod impl_ext;
mod impl_helper;

fn safe_calc<'local, F>(env: &mut JNIEnv<'local>, calc: F) -> JObject<'local>
where
    F: Fn() -> Option<MagneticField>,
{
    match calc() {
        None => {
            env.throw_new("java/lang/IllegalArgumentException", "Not within the validity period")
                .unwrap();

            JObject::null()
        }
        Some(m) => match env.new_magnetic_field(&m) {
            Ok(obj) => obj,
            Err(err) => {
                env.throw_new("java/lang/IllegalArgumentException", err.to_string())
                    .unwrap();

                JObject::null()
            }
        },
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
    safe_calc(&mut env, || Geomag::wmm_d(l, decimal))
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
    safe_calc(&mut env, || Geomag::igrf_d(l, decimal))
}
