package com.damare.main;

import com.damare.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static com.damare.model.ApplicationState.getInstance;

public class HomeController {
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    private LineChart lineChart;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button friendsBtn;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private ListView<Task> taskListView;

    @FXML
    private ListView<Category> categoryListView;
    @FXML
    private Button addTaskBtn;

    @FXML
    private Button leaderBoardBtn;


    private Object state;


    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();

        loadMyTasks();
        loadMyCategories();
        lineChart();

        logoutBtn.setOnAction(ControllerUtils::toLogin);

        addTaskBtn.setOnAction(ControllerUtils::toAddTask);
        addCategoryBtn.setOnAction(ControllerUtils::toAddCategory);
        friendsBtn.setOnAction(ControllerUtils::toUsers);
        updateUserBtn.setOnAction(ControllerUtils::toUpdateUser);
        leaderBoardBtn.setOnAction(ControllerUtils::toLeaderBoard);
        taskListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (!empty) {
                    setText(task.getName());

                    Button delete = new Button("delete");
                    delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ControllerUtils.removeTask(task.getId());
                            loadMyTasks();
                        }
                    });
                    Button google = new Button("google");
                    google.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {

                                Desktop.getDesktop().browse(new URI(ControllerUtils.createGoogleLink(task.getDate(), task.getName(), task.getDuration())));

                            } catch (IOException | URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Button done = new Button("done");
                    done.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ControllerUtils.finishTask(task.getId());
                            loadMyTasks();
                        }
                    });

                    setGraphic(new HBox(delete, google, done));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });


        categoryListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (!empty) {
                    setText(category.getName());

                    Button delete = new Button("delete");
                    delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ControllerUtils.removeCategory(category.getId());
                            loadMyCategories();
                        }
                    });


                    setGraphic(new HBox(delete));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }


    private void loadMyTasks() {

        taskListView.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        taskListView.getItems().addAll(ControllerUtils.viewTasks(currentUser.getId()));
    }

    private void loadMyCategories() {

        categoryListView.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        categoryListView.getItems().addAll(ControllerUtils.viewCategories(currentUser.getId()));
    }

    public void clickOnLoadedTask(MouseEvent mouseEvent) {
        this.state = getInstance();
        Task clicked = taskListView.getSelectionModel().getSelectedItem();

        ActionEvent ae = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
        ApplicationState.getInstance().setCurrentlyEditedTask(clicked);
        if (clicked == null) return;
        ControllerUtils.toUpdateTask(ae);


    }

    public void clickOnLoadedCategory(MouseEvent mouseEvent) {
        this.state = getInstance();
        Category clicked = categoryListView.getSelectionModel().getSelectedItem();

        System.out.println(clicked.getId());
        if (clicked == null) return;


    }

    private void lineChart() {
        this.state = getInstance();
        User currentUser = getInstance().getCurrentlyLoggedUser();

        int[] intArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


        Collection<Task> list = taskListView.getItems();
        for (Task task : list) {
            Date date = task.getDate();
            if (date.getYear() + 1900 == LocalDate.now().getYear()) {
                System.out.println(task.getDate().getYear());
                System.out.println(LocalDate.now().getYear());
                if (date.getMonth() + 1 == LocalDate.now().getMonthValue()) {
                    int datum = date.getDate();

                    intArray[datum]++;
                }
            }
        }

        XYChart.Series series = new XYChart.Series();

        int pocetDni = getNumberOfDaysInMonth(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        for (int i = 1; i <= pocetDni; i++) {

            series.getData().add(new XYChart.Data(String.valueOf(i), intArray[i]));
        }

        lineChart.getData().addAll(series);


    }

    public static int getNumberOfDaysInMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }

}
