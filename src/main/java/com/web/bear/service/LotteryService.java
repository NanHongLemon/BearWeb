package com.web.bear.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.web.bear.model.UserExcelModel;
import com.web.bear.model.UserLotteryModel;
import com.web.bear.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class LotteryService {

    @Autowired
    private HttpServletRequest request;

    public String processLotteryUser(String data, int lotteryNumber, int waitingNumber) {

        List<UserExcelModel> user = JsonUtil.jsonToObject(data, new TypeReference<List<UserExcelModel>>() {
        });

        Collections.shuffle(user);
        if (lotteryNumber > user.size()) {
            return JsonUtil.objectToJson(user);
        }

        UserLotteryModel outputUser = new UserLotteryModel();
        processStaticAdmissionUser(outputUser, user, lotteryNumber);

        int staticUserNumber = outputUser.getAdmissions().size();
        int admissionSize = lotteryNumber - staticUserNumber;
        for (int i = 0; i < admissionSize; i++) {
            outputUser.getAdmissions().add(user.get(i));
        }
        Collections.shuffle(outputUser.getAdmissions());

        int index = admissionSize;
        if (index >= 0) {
            int waitingSize = user.size() - index >= waitingNumber ? waitingNumber : user.size() - index;
            for (int i = 0; i < waitingSize; i++) {
                outputUser.getWaitings().add(user.get(index + i));
            }
        }

        return JsonUtil.objectToJson(outputUser);
    }

    private List<UserExcelModel> getSessionStaticUser() {

        HttpSession session = request.getSession();
        List<UserExcelModel> staticUser = (List<UserExcelModel>)session.getAttribute("staticUser");
        if (staticUser == null) {
            staticUser = new ArrayList<>();
            UserExcelModel item = new UserExcelModel();
            item.setId(1);
            item.setName("薛薛");
            staticUser.add(item);
            return staticUser;
        }
        return staticUser;
    }

    private void processStaticAdmissionUser(UserLotteryModel outputUser, List<UserExcelModel> user, int lotteryNumber) {

        int count = 0;
        List<UserExcelModel> staticUser = getSessionStaticUser();
        if (staticUser.isEmpty()) {
            return;
        }
        Collections.shuffle(staticUser);
        for (int i = 0; i < staticUser.size(); i++) {
            UserExcelModel userItem = staticUser.get(i);
            Iterator<UserExcelModel> it = user.iterator();
            while (it.hasNext()) {
                UserExcelModel inputItem = it.next();
                if (count < lotteryNumber && userItem.getId().equals(inputItem.getId()) && userItem.getName().equals(inputItem.getName())) {
                    outputUser.getAdmissions().add(inputItem);
                    it.remove();
                    count++;
                }
            }
        }
    }

    public void saveStaticLottery(String data) {

        List<UserExcelModel> list = JsonUtil.jsonToObject(data, new TypeReference<List<UserExcelModel>>() {
        });
        HttpSession session = request.getSession();
        session.setAttribute("staticUser", list);
    }
}
