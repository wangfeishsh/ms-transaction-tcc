分布式事务的三个角色
* Participants 是那些第三方服务提供者希望他们的服务被TCC-aware .
* The coordinator service 我们提供的协调服务是：一个可重用的服务来管理一致的确认（或取消）的一组相关的服务调用（包括恢复）。
* the application 使用协调者的API

#### Participants
1. Autonomous Timeout and Cancel 超时后自发取消处理
2. Participant Link 预留资源后，必须返回一个可以用于后续确认动作的链接（里面包含过期时间）
 ~~~
{
    "participantLink": {
        "uri": "http://www.example.com/part/123",
        "expires": "2014-01-11T10:15:54Z"
    }
}
 ~~~
3. PUT to Confirm 资源预留后可以根据提供的uri使用PUT方式进行确认
4. Optional: DELETE to Cancel 资源预留后可以根据提供的uri使用方式进行cancel进行取消
5. Optional: GET for Failure Diagnostics

#### coordinator service

1. PUT to Confirm 
    * 参与者均成功
    * 参与者均超时
    * 参与者部分成功conflict
    
    当然，理想情况是尽量减少发生这种情况的数量——这是协调员职责的一个重要部分。
    但是，如果发生这种情况，则检查受影响的参与者，可能是通过向每个参与者URI发出GET请求。
    然后需要某种手动解决方案，具体的细节是故意遗漏在这个规范中以允许每个应用程序使用最适合的方法。
2. PUT to Cancel