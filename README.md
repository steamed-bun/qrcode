# qrcode
生成二维码，类似于QQ，图中二维码颜色大小位置，还有原始图片的形状都是可以自定义的。

原始图片：

![原始图片](https://github.com/steamed-bun/qrcode/blob/master/src/main/resources/static/fff.png)

生成的二维码图片:(图中二维码为百度链接)

![生成的二维码图片](https://github.com/steamed-bun/qrcode/blob/master/src/main/resources/static/%E8%BE%93%E5%87%BA%E7%9A%84%E4%BA%8C%E7%BB%B4%E7%A0%81%E5%9B%BE%E7%89%87.png)


**注意：** 

* 原始图片背景必须是白色，其他部分不限制---需要改进

* 生成的二维码大小目前不能大于原始图片大小---需要改进

此代码是用了 Zxing 的源码，对主要代码做了一些注释，并筛掉了一些多余的部分，还需进一步筛除多余的；

wx.zxing.bitArray和RandomIntArray包下面的是自己的测试文件，主要作用如下：

[用 int 来储存 boolean 数组](http://www.jianshu.com/p/899ce9f26441)

[生成随机0、1矩阵](http://www.jianshu.com/p/e2d557cf1226)
