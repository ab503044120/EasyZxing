
#序言

最近项目需要使用Zxing扫码功能在github中看了一些库,发现这些库的CaptureActiviy和其他类的耦合性太高了,
如果你需要系定义activity的话还需要修改很大,本库把耦合的部分全部抽取到 Zxingmanager.java 中
因此无论你是在activity,fragment 都可以使用.

支持二维码和条码两种模式,支持扫码成功beep振动,支持扫码成功后预览

#使用方法

参考 CaptureActivity.java
##1.创建初始化ZxingManager
```
/**
 * @param context                 activity 必要
 * @param viewFinderViewInterface 刷新的viev
 * @param surfaceHolder           surfaceholder
 * @param isOneD                  false:支持条码和二维码 true:只支持条码
 * @param autoShutDown            支持长时间自动关闭
 */
mZxingManager = new ZxingManager(this, viewfinderView, view.getHolder(), false, true);
//初始化
mZxingManager.init();
//设置获取结果后的监听
mZxingManager.setZxingManagerListener(new ZxingManager.ZxingManagerListener() {
          @Override
          public void onSuccess(Result result, Bitmap barcode) {
              //do something
          }
      });
```
## 2.写生命周期
```
@Override
protected void onResume() {
    super.onResume();
    mZxingManager.onResume();
}
@Override
protected void onPause() {
    super.onPause();
    mZxingManager.onPause();
}
@Override
protected void onDestroy() {
    super.onDestroy();
    mZxingManager.release();
}
```
##3.扫码完成后继续扫码
```
 /**
 * 毫秒值
 * */
mZxingManager.restartScan(1000);
```
jcenter地址: 
```
compile 'org.huihui:easyzxing:1.0.6'
```
**最后希望大家多提点建议来改进,喜欢的可以来个star鼓励一下**
