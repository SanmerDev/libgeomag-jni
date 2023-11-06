use jni::errors::Result;
use jni::objects::JObject;
use jni::JNIEnv;
use libgeomag::MagneticField;

use crate::impl_ext::JNIEnvExt;

pub trait JNIEnvHelper<'local> {
    fn new_magnetic_field(&mut self, m: &MagneticField) -> Result<JObject<'local>>;
}

impl<'local> JNIEnvHelper<'local> for JNIEnv<'local> {
    fn new_magnetic_field(&mut self, m: &MagneticField) -> Result<JObject<'local>> {
        let class = self.find_class("dev/sanmer/geomag/MagneticField")?;
        let obj = self.alloc_object(class)?;

        self.set_double_field(&obj, "x", m.x)?;
        self.set_double_field(&obj, "xDot", m.x_dot)?;
        self.set_double_field(&obj, "y", m.y)?;
        self.set_double_field(&obj, "yDot", m.y_dot)?;
        self.set_double_field(&obj, "z", m.z)?;
        self.set_double_field(&obj, "zDot", m.z_dot)?;
        self.set_double_field(&obj, "h", m.h)?;
        self.set_double_field(&obj, "hDot", m.h_dot)?;
        self.set_double_field(&obj, "f", m.f)?;
        self.set_double_field(&obj, "fDot", m.f_dot)?;
        self.set_double_field(&obj, "d", m.d)?;
        self.set_double_field(&obj, "dDot", m.d_dot)?;
        self.set_double_field(&obj, "i", m.i)?;
        self.set_double_field(&obj, "iDot", m.i_dot)?;

        Ok(obj)
    }
}
