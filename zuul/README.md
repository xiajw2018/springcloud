# Zuul
**zuul是作为一个servlet被实现的，默认会缓冲请求。如果需要通过Zuul而不缓冲请求（例如，用于大文件上传）。则需要在路由地址前，添加/zuul。**
**可以使用zuul.````servlet-path属性更改此路径**

 Zuul内部使用Ribbon来调用远程URL。
 
 # spring cloud 团队已经停止更新zuul，后面会替换为gateway网关