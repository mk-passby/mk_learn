# RMI
## 实现一个RMI程序
- 编写服务端程序，暴露端口监听（LocateRegistry.createRegistry(8888);）
- 绑定对象到对应接口(Naming.bind("rmi://localhost:8888/test",hello);)
- 客户端寻找接口(Hello hello=(Hello) Naming.lookup("rmi://localhost:8888/test");)

注意：

- 接口hello必须继承java.rmi.Remote(接口方法抛出REMOTEEXCEPTION)
- 实现类继承java.rmi.server.UnicastRemoteObject对象

