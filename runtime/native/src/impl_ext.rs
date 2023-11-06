use jni::errors::Result;
use jni::objects::{JObject, JValue};
use jni::sys::jdouble;
use jni::JNIEnv;

pub trait JNIEnvExt {
    fn set_double_field(&mut self, obj: &JObject, name: &str, val: jdouble) -> Result<()>;
}

impl<'local> JNIEnvExt for JNIEnv<'local> {
    fn set_double_field(&mut self, obj: &JObject, name: &str, val: jdouble) -> Result<()> {
        self.set_field(obj, name, "D", JValue::from(val))
    }
}
