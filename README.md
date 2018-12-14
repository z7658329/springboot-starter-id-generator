# springboot-starter-id-generator
springboot-starter-id-generator
第一版本参考雪花算法实现的java版本

第二版规划：
      解决分布式服务ID生成器中的时钟回调问题（https://github.com/baidu/uid-generator/issues/10）
      
      解决思路：分布式集群中 每台server提供一个获取当前系统时间的服务
      假设serverA重启，则从集群的servers列表里获取所有server的时间戳，取一个平均值SERVER_AVG_TIME
      如果serverA的当前时间戳为TIME_A  
      if( (TIME_A -  SERVER_AVG_TIME) >= 1000) {
        THROWS SYSTEMEXCEPTION("启动异常，系统时钟回调过大，请联系运维紧急处理");
      }
      
