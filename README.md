# Big-test
这次的大作业是在之前的登录注册上面写的，实现了以下功能：
1.登陆注册
2.网络请求 获得首页文章
3.下拉刷新
4.菜单点击时切换图标
5.viewpage滑动动画
6.菜单与viewpage相互对应

以下对各个class的注解：
article。class：为获取的文章类，包含三个属性
Data和data2：用于gson解析
Datauser：用于实现登录注册的类
homefragment：为首页对应的fragment
mainactivity：登录
mainactivity2:注册
peoplefragment：为“我的”对应的fragment
rvadapter：recycleview的适配器
wanandriod：放了viewpage 和菜单
Yourarticl：当recycleview点击时的跳转页面
zoomoutpagetransformer：viewpage的滑动动画

遇到的问题：
1.我最先找不到空间里面的bottonnavigation，然后是通过建立那个有菜单的activity，然后把里面的菜单做一些删除，才能够使用。
2.对菜单backgroud的设置，原本想将菜单设置为黄色，但是在xml里面设置后，黄色并不能填充完整，最后放弃，白色也挺好的（不是）
3.对图标的优化，对图标的点击时做出了优化，使点击时图片改变，在xml写的代码是未点击时是黑色的图片，点击时是黄色的图片，但是运行出来全是黄色，时间有点紧，就没去解决了，看着还是可以。
4。重头戏：网络请求，以及recycleview的适配器。跟着哲哥的直播走的，所以代码差不多。但是还是遇到了一些问题。
   fragment findviewbyid()同样无法使用，需改为getview().findviewbyid()
   如果把那一大堆程序全部搬运到oncreatview（）里面，直接用view.findviewbyid()就可以，所以之后的代码我都是卸载了oncreatview（）里面
   调式的时候，数据一直返回空指针，app闪退，我先用log。e将网络请求的数据流打印出来，发现并不是数据的错误，然后就是疯狂的检查代码，发现再Data类中，属性命名出现错误我的是data2，应该和数据流里面的data保持一致不然无法解析
   修改后网络请求成功，之后就是其他方面的优化，包括用view作为分割线，点击特效，点击跳转，以及滑动效果
   在写点击调转时，遇到了getview().findviewbyid()返回空指针问题，后将代码转移到oncreatview（）用view.findviewbyid()解决
