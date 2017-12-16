package com.soapboxrace.core.api.util;

import com.soapboxrace.core.jpa.BanEntity;
import com.soapboxrace.jaxb.login.LoginStatusVO;

import java.time.format.DateTimeFormatter;

public class BanUtil
{
    private static final DateTimeFormatter banEndFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LoginStatusVO getLoginStatus(BanEntity banEntity)
    {
        LoginStatusVO loginStatusVO = new LoginStatusVO(0L, "", false);
        LoginStatusVO.Ban ban = new LoginStatusVO.Ban();
        ban.setReason(banEntity.getReason());
        ban.setExpires(banEntity.getEndsAt() == null ? null : banEndFormatter.format(banEntity.getEndsAt()));

        loginStatusVO.setBan(ban);

        return loginStatusVO;
    }
}
