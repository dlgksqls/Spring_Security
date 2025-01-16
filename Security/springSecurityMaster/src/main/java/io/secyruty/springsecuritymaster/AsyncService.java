package io.secyruty.springsecuritymaster;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async // 비동기 실행을 할 수 있도록 설정해줌
    public void asyncMethod(){
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("securityContext = " + securityContext);
        System.out.println("Parent Thread: " + Thread.currentThread().getName());
    }
}
