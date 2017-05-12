/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.schedule;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import com.tomcatisbabycat.homepanel.sampleAppliance.AList;
import com.tomcatisbabycat.homepanel.sampleAppliance.Appliances;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class ScheduleController implements Initializable {

      @FXML
      private StackPane scheduleStackPane;
      @FXML
      private Button btnControlHome;
      @FXML
      private Button btnControlLock;
      @FXML
      private Button btnControlBack;
      @FXML
      private Button btnTV;
      @FXML
      private Button btnAirCondition;
      @FXML
      private Button btnWashingMachine;
      @FXML
      private Button btnLight;
      @FXML
      private Label lblSchedule;
      @FXML
      private ListView<Appliances> scheduleListview;
      @FXML
      private Button btnAdd;

      private AList aList = AList.getInstance();

      /**
       * Initializes the controller class.
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            btnControlLock.setOnAction(event -> {
                  handleBtnControlLock(event);
            });
            btnControlHome.setOnAction(event -> {
                  handleBtnControlHome(event);
            });
            btnControlBack.setOnAction(event -> {
                  handleBtnControlBack(event);
            });
            btnTV.setDefaultButton(true);

            if (btnTV.isDefaultButton()) {
                  defaultButton();
            }
            //ToggleSwitch ts = new ToggleSwitch();
            // 처음 씬에 들어갔을때
            scheduleListview.setItems(aList.getTv());

            btnTV.setOnAction(event -> {
                  handleBackground(event);
                  scheduleListview.setItems(aList.getTv());
                  System.out.println(scheduleListview.getItems().size());
            });
            btnAirCondition.setOnAction(event -> {
                  handleBackground(event);
                  scheduleListview.setItems(aList.getAc());
                  System.out.println(scheduleListview.getItems().size());
            });
            btnWashingMachine.setOnAction(event -> {
                  handleBackground(event);
                  scheduleListview.setItems(aList.getWm());
                  System.out.println(scheduleListview.getItems().size());
            });
            btnLight.setOnAction(event -> {
                  handleBackground(event);
                  scheduleListview.setItems(aList.getLight());
                  System.out.println(scheduleListview.getItems().size());

            });
            scheduleListview.setCellFactory(new Callback<ListView<Appliances>, ListCell<Appliances>>() {
                  @Override
                  public ListCell<Appliances> call(ListView<Appliances> param) {
                        ListCell<Appliances> listCell = new ListCell<Appliances>() {
                              @Override
                              protected void updateItem(Appliances item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                          setText(null);
                                          setGraphic(null);
                                          return;
                                    }
                                    try {
                                          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("appliances.fxml"));
                                          Label lblName = (Label) a.lookup("#name");
                                          Label lblTime = (Label) a.lookup("#turnTime");
                                          ImageView btnDelImg = (ImageView) a.lookup("#btnDelImg");
                                          Label lblOnOff = (Label) a.lookup("#onOff");
                                          ToggleSwitch ts = (ToggleSwitch) a.lookup("#btnRun");

                                          //item.setTs(ts.switchedOnProperty());
                                          lblName.setText(item.getLblName());
                                          lblTime.setText(item.getTurnTime());
                                          lblOnOff.setText(item.getOn());
                                          btnDelImg.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                                                selectedDelete(item);

                                          });
                                          ts.switchedOnProperty().addListener((observable, oldValue, newValue) -> {
                                                if (item.getCategory().equals("전등")) {
                                                      if (newValue.toString().equals("true")) {
                                                            System.out.println("newValue = true");
                                                            item.lightOnOff(item.getTurnTime(), item.getLblName(), item.getOn());
                                                      } else if (newValue.toString().equals("false")) {
                                                            System.out.println("newValue = false");
                                                            item.getExec().shutdown();
                                                      }
                                                }
                                          });

                                          setGraphic(a);

                                    } catch (IOException ex) {
                                          ex.printStackTrace();
                                    }

                              }

                        };

                        return listCell;
                  }

                  @Override
                  protected void finalize() throws Throwable {
                        System.out.println("Factory Out");
                  }

            });

            btnAdd.setOnAction(e -> {
                  handleAddButton(e);
            });
      }

      private void handleAddButton(ActionEvent e) {
            try {
                  Parent parent = FXMLLoader.load(getClass().getResource("scheduleAdd.fxml"));
                  scheduleStackPane.getChildren().add(parent);

                  Rectangle rec = (Rectangle) parent.lookup("#popupBackground");
                  AnchorPane anp = (AnchorPane) parent.lookup("#addAnchorPane");
                  Button btnExit = (Button) parent.lookup("#btnExit");

                  Button btnAdd = (Button) parent.lookup("#btnAdd");
                  ComboBox<String> category = (ComboBox<String>) parent.lookup("#comboCategory");
                  ComboBox<String> name = (ComboBox<String>) parent.lookup("#comboName");
                  ToggleSwitchSmall btnOnOff = (ToggleSwitchSmall) parent.lookup("#btnOnOff");
                  TimeSpinner timeSpinner = (TimeSpinner) parent.lookup("#timeSpinner");
                  //parent.setDisable(true);

                  btnExit.setOnAction(event -> {
                        Timeline timeline = new Timeline();
                        KeyValue keyvalue = new KeyValue(anp.opacityProperty(), 0);
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
                              Timeline timeline2 = new Timeline();
                              KeyValue keyvalue2 = new KeyValue(rec.opacityProperty(), 0);
                              KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), (e2) -> {
                                    scheduleStackPane.getChildren().remove(2);
                              }, keyvalue2);
                              timeline2.getKeyFrames().add(keyFrame2);
                              timeline2.play();
                        }, keyvalue);

                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();

                        scheduleStackPane.getChildren().get(2).setDisable(true);
                  });
                  btnAdd.setOnAction(event -> {
                        addListView(category, name, timeSpinner, btnOnOff);

                        Timeline timeline = new Timeline();
                        KeyValue keyvalue = new KeyValue(anp.opacityProperty(), 0);
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
                              Timeline timeline2 = new Timeline();
                              KeyValue keyvalue2 = new KeyValue(rec.opacityProperty(), 0);
                              KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), (e2) -> {
                                    scheduleStackPane.getChildren().remove(2);
                              }, keyvalue2);
                              timeline2.getKeyFrames().add(keyFrame2);
                              timeline2.play();
                        }, keyvalue);

                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();

                        scheduleStackPane.getChildren().get(2).setDisable(true);

                  });
            } catch (IOException ex) {

            }
      }

      private void selectedDelete(Appliances item) {
            try {
                  //scheduleListview.getItems().remove(newValue);
                  Parent parent = FXMLLoader.load(getClass().getResource("scheduleDelete.fxml"));
                  scheduleStackPane.getChildren().add(parent);
                  System.out.println("Parent : " + parent);
                  Rectangle rec = (Rectangle) parent.lookup("#deleteBackground");
                  AnchorPane anp = (AnchorPane) parent.lookup("#deleteAnchorPane");
                  Button btnOk = (Button) parent.lookup("#btnOk");
                  Button btnCancel = (Button) parent.lookup("#btnCancel");

                  System.out.println("btnOk : " + btnOk);
                  System.out.println("btnOk : " + btnCancel);

                  btnCancel.setOnAction(event -> {
                        Timeline timeline = new Timeline();
                        KeyValue keyvalue = new KeyValue(anp.opacityProperty(), 0);
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
                              Timeline timeline2 = new Timeline();
                              KeyValue keyvalue2 = new KeyValue(rec.opacityProperty(), 0);
                              KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), (e2) -> {
                                    scheduleStackPane.getChildren().remove(2);
                              }, keyvalue2);
                              timeline2.getKeyFrames().add(keyFrame2);
                              timeline2.play();
                        }, keyvalue);

                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                        scheduleStackPane.getChildren().get(2).setDisable(true);
                  });
                  btnOk.setOnAction(event -> {
                        scheduleListview.getItems().remove(item);
                        item.getExec().shutdown();

                        Timeline timeline = new Timeline();
                        KeyValue keyvalue = new KeyValue(anp.opacityProperty(), 0);
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
                              Timeline timeline2 = new Timeline();
                              KeyValue keyvalue2 = new KeyValue(rec.opacityProperty(), 0);
                              KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), (e2) -> {
                                    scheduleStackPane.getChildren().remove(2);
                              }, keyvalue2);
                              timeline2.getKeyFrames().add(keyFrame2);
                              timeline2.play();
                        }, keyvalue);

                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                        scheduleStackPane.getChildren().get(2).setDisable(true);
                  });
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      private void handleBackground(ActionEvent event) {
            Button btn = (Button) event.getSource();

            btnTV.getStyleClass().removeAll("conditionBtnFocused");
            btnTV.getStyleClass().add("conditionBtn");
            btnAirCondition.getStyleClass().removeAll("conditionBtnFocused");
            btnAirCondition.getStyleClass().add("conditionBtn");
            btnWashingMachine.getStyleClass().removeAll("conditionBtnFocused");
            btnWashingMachine.getStyleClass().add("conditionBtn");
            btnLight.getStyleClass().removeAll("conditionBtnFocused");
            btnLight.getStyleClass().add("conditionBtn");

            btn.getStyleClass().removeAll("conditionBtn");
            btn.getStyleClass().add("conditionBtnFocused");

      }

      private void defaultButton() {
            btnTV.getStyleClass().add("conditionBtnFocused");
      }

      private void handleBtnControlBack(ActionEvent event) {
            try {
                  StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
                  LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
                  // 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
                  // 현재상태에서 메뉴의 인덱스는 2

                  // 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
                  parent.getChildren().get(0).setOpacity(0);
                  parent.getChildren().get(1).setOpacity(0);
                  //parent.setTranslateX(800);

                  KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
                  KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
                        Timeline timeline2 = new Timeline();
                        KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
                        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
                        timeline2.getKeyFrames().add(keyFrame2);
                        timeline2.play();
                        LockController.lockRootPane.getChildren().remove(1);
                  }, keyValueStackPane);

                  // 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
                  //KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
                  //KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
                  Timeline timeline = new Timeline();
                  timeline.getKeyFrames().addAll(keyFrameStackPane);
                  timeline.play();
            } catch (IOException ex) {
            }
      }

      private void handleBtnControlLock(ActionEvent event) {
            LockController.lockRootPane.getChildren().remove(scheduleStackPane);
      }

      private void handleBtnControlHome(ActionEvent event) {
            try {
                  StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
                  LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
                  // 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
                  // 현재상태에서 메뉴의 인덱스는 2

                  // 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
                  parent.getChildren().get(0).setOpacity(0);
                  parent.getChildren().get(1).setOpacity(0);
                  //parent.setTranslateX(800);

                  KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
                  KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
                        Timeline timeline2 = new Timeline();
                        KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
                        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
                        timeline2.getKeyFrames().add(keyFrame2);
                        timeline2.play();
                        LockController.lockRootPane.getChildren().remove(1);
                  }, keyValueStackPane);

                  // 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
                  //KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
                  //KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
                  Timeline timeline = new Timeline();
                  timeline.getKeyFrames().addAll(keyFrameStackPane);
                  timeline.play();
            } catch (IOException ex) {
            }
      }

      private void addListView(ComboBox<String> category, ComboBox<String> name, TimeSpinner timeSpinner, ToggleSwitchSmall btnOnOff) {
            System.out.println("addListView\n");
            //System.out.println(scheduleListview.getItems().size());
            if (category.getSelectionModel().getSelectedItem().equals("전등")) {
                  aList.getLight().add(new Appliances(category.getSelectionModel().getSelectedItem(),
                          name.getSelectionModel().getSelectedItem(),
                          timeSpinner.getEditor().getText(),
                          btnOnOff.buttonString()));

            } else if (category.getSelectionModel().getSelectedItem().equals("TV")) {

                  aList.getTv().add(new Appliances(category.getSelectionModel().getSelectedItem(),
                          name.getSelectionModel().getSelectedItem(),
                          timeSpinner.getEditor().getText(),
                          btnOnOff.buttonString()));

            } else if (category.getSelectionModel().getSelectedItem().equals("에어컨")) {

                  aList.getAc().add(new Appliances(category.getSelectionModel().getSelectedItem(),
                          name.getSelectionModel().getSelectedItem(),
                          timeSpinner.getEditor().getText(),
                          btnOnOff.buttonString()));

            } else if (category.getSelectionModel().getSelectedItem().equals("세탁기")) {

                  aList.getWm().add(new Appliances(category.getSelectionModel().getSelectedItem(),
                          name.getSelectionModel().getSelectedItem(),
                          timeSpinner.getEditor().getText(),
                          btnOnOff.buttonString()));

            }

      }

      @Override
      protected void finalize() throws Throwable {
            System.out.println("ScheduleController is OUT!!!!");
      }
}
