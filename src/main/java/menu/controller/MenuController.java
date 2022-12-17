package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.CoachList;
import menu.domain.RandomLottery;
import menu.domain.Remark;
import menu.domain.Result;
import menu.domain.ResultList;
import menu.domain.UserList;
import menu.service.InputViewService;
import menu.service.RemoveMenu;
import menu.utils.Constants;
import menu.view.OutputView;
import org.assertj.core.util.Arrays;

public class MenuController {

    private final InputViewService inputViewService;
    private final OutputView outputView;
    private static List<String> results;

    public MenuController() {
        this.inputViewService = new InputViewService();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.print(Constants.START_MESSAGE);

        CoachList coachList = readCoachList();

        UserList menuList = readRemoveMenu(coachList);

        recommendMenu(menuList);

    }

    public CoachList readCoachList() {
        return inputViewService.getReadCoachList();
    }

    public UserList readRemoveMenu(CoachList coachList) {

        List<Remark> remarks = new ArrayList<>();

        for (Coach coach : coachList.getCoachList()) {
            remarks.add(
                Remark.of(coach, RemoveMenu.removeMenu(inputViewService.getReadRemoveList(coach))));
        }

        return UserList.from(remarks);
    }

    public void recommendMenu(UserList userList) {
        RandomLottery randomLottery = new RandomLottery();
        outputView.print(Constants.RESULT_MESSAGE);
        List<String> categoryResult = new ArrayList<>();
        List<String> randomNumbers = new ArrayList<>();
        categoryResult.add("카테고리");

        for (int i = 0; i < 5; ++i) {
            int number = randomLottery.setRandomNumber();
            randomNumbers.add(Integer.toString(number));
            String menu;

            List<String> category = java.util.Arrays.asList("일식", "한식", "중식", "아시안", "양식");

            menu = randomLottery.setShuffleMenu(category);

            categoryResult.add(menu);

        }
        outputView.printResult(new ResultList(categoryResult));
        recommendMenuName(randomNumbers);
    }
    public void recommendMenuName(List<String> randomNumbers) {
        List<String> categoryResult = new ArrayList<>();


    }


}
