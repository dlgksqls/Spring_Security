package io.secyruty.springsecuritymaster;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionInfoService {

    private final SessionRegistry sessionRegistry;

    public void sessionInfo(){
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (Object principal : allPrincipals) {
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(principal, false);

            for (SessionInformation sessionInformation : sessionInformations) {
                System.out.println("사용자 : " + principal + " 세션 ID : " + sessionInformation.getSessionId()
                        + " 최종 요청 시간 : " + sessionInformation.getLastRequest());
            }
        }
    }
}
