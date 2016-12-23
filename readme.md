#使用方法

参考 CaptureActivity.java
##1.创建初始化ZxingManager
`
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
`
## 2.写生命周期
`
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
`


