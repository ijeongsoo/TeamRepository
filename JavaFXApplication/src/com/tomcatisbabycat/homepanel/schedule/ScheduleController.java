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
	@FXML
	private Rectangle menuBack;
	@FXML
	private Label lblHide;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lblHide.toFront();
		
		btnAdd.setFocusTraversable(false);
		btnAirCondition.setFocusTraversable(false);
		btnControlBack.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);
		btnLight.setFocusTraversable(false);
		btnTV.setFocusTraversable(false);
		btnWashingMachine.setFocusTraversable(false);

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
		scheduleListview.refresh();
		scheduleListview.setItems(aList.getTv());

		if (scheduleListview.getItems().size() == 0) {
			lblHide.setVisible(true);
		} else {
			lblHide.setVisible(false);
		}

		btnTV.setOnAction(event -> {
			handleBackground(event);
			scheduleListview.refresh();
			scheduleListview.setItems(aList.getTv());
			if (scheduleListview.getItems().size() == 0) {
				lblHide.setVisible(true);
			} else {
				lblHide.setVisible(false);
			}
		});
		btnAirCondition.setOnAction(event -> {
			handleBackground(event);
			scheduleListview.refresh();
			scheduleListview.setItems(aList.getAc());
			if (scheduleListview.getItems().size() == 0) {
				lblHide.setVisible(true);
			} else {
				lblHide.setVisible(false);
			}
		});
		btnWashingMachine.setOnAction(event -> {
			handleBackground(event);
			scheduleListview.refresh();
			scheduleListview.setItems(aList.getWm());
			if (scheduleListview.getItems().size() == 0) {
				lblHide.setVisible(true);
			} else {
				lblHide.setVisible(false);
			}
		});
		btnLight.setOnAction(event -> {
			handleBackground(event);
			scheduleListview.refresh();
			scheduleListview.setItems(aList.getLight());
			if (scheduleListview.getItems().size() == 0) {
				lblHide.setVisible(true);
			} else {
				lblHide.setVisible(false);
			}
		});

		btnAdd.setOnAction(e -> {
			handleAddButton(e);
		});
		scheduleListview.setCellFactory(new Callback<ListView<Appliances>, ListCell<Appliances>>() {
			@Override
			public ListCell<Appliances> call(ListView<Appliances> param) {
				ListCell<Appliances> listCell = new ListCell<Appliances>() {
					@Override
					protected void updateItem(Appliances item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty == true) {
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
							ToggleSwitch toggleSwitch = (ToggleSwitch) a.lookup("#toggleSwitch");

							Label lblMon = (Label) a.lookup("#lblMon");
							Label lblTue = (Label) a.lookup("#lblTue");
							Label lblWen = (Label) a.lookup("#lblWen");
							Label lblThu = (Label) a.lookup("#lblThu");
							Label lblFri = (Label) a.lookup("#lblFri");
							Label lblSat = (Label) a.lookup("#lblSat");
							Label lblSun = (Label) a.lookup("#lblSun");

							lblMon.setText(item.getMon());
							lblTue.setText(item.getTue());
							lblWen.setText(item.getWen());
							lblThu.setText(item.getThu());
							lblFri.setText(item.getFri());
							lblSat.setText(item.getSat());
							lblSun.setText(item.getSun());

							Button mon = (Button) a.lookup("#btnMon");
							Button tue = (Button) a.lookup("#btnTue");
							Button wen = (Button) a.lookup("#btnWen");
							Button thu = (Button) a.lookup("#btnThu");
							Button fri = (Button) a.lookup("#btnFri");
							Button sat = (Button) a.lookup("#btnSat");
							Button sun = (Button) a.lookup("#btnSun");

							lblName.setText(item.getLblName());
							lblTime.setText(item.getTurnTime());
							lblOnOff.setText(item.getOn());

							if (lblMon.getText().equals("true")) {
								//System.out.println("1. " + lblMon.getText());
								mon.getStyleClass().remove("dayButton");
								mon.getStyleClass().add("dayButtonClicked");
							}
							if (lblTue.getText().equals("true")) {
								//System.out.println("2. " + lblWen.getText());
								tue.getStyleClass().remove("dayButton");
								tue.getStyleClass().add("dayButtonClicked");
							}
							if (lblWen.getText().equals("true")) {
								//System.out.println("3. " + lblTue.getText());
								wen.getStyleClass().remove("dayButton");
								wen.getStyleClass().add("dayButtonClicked");
							}
							if (lblThu.getText().equals("true")) {
								//System.out.println("4. " + lblThu.getText());
								thu.getStyleClass().remove("dayButton");
								thu.getStyleClass().add("dayButtonClicked");
							}
							if (lblFri.getText().equals("true")) {
								//System.out.println("5. " + lblFri.getText());
								fri.getStyleClass().remove("dayButton");
								fri.getStyleClass().add("dayButtonClicked");
							}
							if (lblSat.getText().equals("true")) {
								//System.out.println("6. " + lblSat.getText());
								sat.getStyleClass().remove("dayButton");
								sat.getStyleClass().add("dayButtonClicked");
							}
							if (lblSun.getText().equals("true")) {
								//System.out.println("7. " + lblSun.getText());
								sun.getStyleClass().remove("dayButton");
								sun.getStyleClass().add("dayButtonClicked");
							}

							btnDelImg.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
								selectedDelete(item);
							});
							if (item.getToggle() != null) {
								toggleSwitch.switchedOnProperty().set(item.getToggle().switchedOnProperty().get());
								toggleSwitch.animate();
							}
							toggleSwitch.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
								if (toggleSwitch.switchedOnProperty().get() == false) {
									item.dayChecker();
									item.setToggle(toggleSwitch);

								} else if (toggleSwitch.switchedOnProperty().get() == true) {
									item.setToggle(toggleSwitch);
									if (item.getExecDay() != null) {
										item.getExecDay().shutdown();
									}
									if (item.getExec() != null) {
										item.getExec().shutdown();
									}
								}
							});

							System.gc();
							setGraphic(a);
						} catch (IOException ex) {
							ex.printStackTrace();
						}

					}

					@Override
					protected void finalize() throws Throwable {
						System.out.println("Cell Out");
					}

				};

				return listCell;
			}

			@Override
			protected void finalize() throws Throwable {
				System.out.println("Factory Out");
			}

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

			Label lblMon = (Label) parent.lookup("#lblMon");
			Label lblTue = (Label) parent.lookup("#lblTue");
			Label lblWen = (Label) parent.lookup("#lblWen");
			Label lblThu = (Label) parent.lookup("#lblThu");
			Label lblFri = (Label) parent.lookup("#lblFri");
			Label lblSat = (Label) parent.lookup("#lblSat");
			Label lblSun = (Label) parent.lookup("#lblSun");

			System.out.println("1. lblMon : " + lblMon.getText());

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
				System.out.println("2. lblMon : " + lblMon.getText());
				addListView(category, name, timeSpinner, btnOnOff,
					  lblMon.getText(), lblTue.getText(), lblWen.getText(), lblThu.getText(), lblFri.getText(), lblSat.getText(), lblSun.getText());
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
				if (scheduleListview.getItems().size() == 0) {
					lblHide.setVisible(true);
				} else {
					lblHide.setVisible(false);
				}

			});
		} catch (IOException ex) {

		}
	}

	private void selectedDelete(Appliances item) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("scheduleDelete.fxml"));
			scheduleStackPane.getChildren().add(parent);
			Rectangle rec = (Rectangle) parent.lookup("#deleteBackground");
			AnchorPane anp = (AnchorPane) parent.lookup("#deleteAnchorPane");
			Button btnOk = (Button) parent.lookup("#btnOk");
			Button btnCancel = (Button) parent.lookup("#btnCancel");

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
				if (item.getExec() != null) {
					item.getExec().shutdown();
				}
				if (item.getExecDay() != null) {
					item.getExecDay().shutdown();
				}
				scheduleListview.refresh();

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

			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);

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

			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}
	}

	private void addListView(ComboBox<String> category, ComboBox<String> name, TimeSpinner timeSpinner, ToggleSwitchSmall btnOnOff,
		  String a, String b, String c, String d, String e, String f, String g) {
		System.out.println("category : " + category.getSelectionModel().getSelectedItem());
		System.out.println("name : " + name.getSelectionModel().getSelectedItem());
		if (category.getSelectionModel().getSelectedItem() == null) {
			System.out.println("category");
		} else if (name.getSelectionModel().getSelectedItem() == null) {
			System.out.println("name");
		} else if (category.getSelectionModel().getSelectedItem().equals("전등")) {
			System.out.println("전등");
			aList.getLight().add(new Appliances(category.getSelectionModel().getSelectedItem(),
				  name.getSelectionModel().getSelectedItem(),
				  timeSpinner.getEditor().getText(),
				  btnOnOff.buttonString(), a, b, c, d, e, f, g));

		} else if (category.getSelectionModel().getSelectedItem().equals("TV")) {
			System.out.println("TV");
			aList.getTv().add(new Appliances(category.getSelectionModel().getSelectedItem(),
				  name.getSelectionModel().getSelectedItem(),
				  timeSpinner.getEditor().getText(),
				  btnOnOff.buttonString(), a, b, c, d, e, f, g));

		} else if (category.getSelectionModel().getSelectedItem().equals("에어컨")) {
			System.out.println("에어컨");
			aList.getAc().add(new Appliances(category.getSelectionModel().getSelectedItem(),
				  name.getSelectionModel().getSelectedItem(),
				  timeSpinner.getEditor().getText(),
				  btnOnOff.buttonString(), a, b, c, d, e, f, g));

		} else if (category.getSelectionModel().getSelectedItem().equals("세탁기")) {
			System.out.println("세탁기");
			aList.getWm().add(new Appliances(category.getSelectionModel().getSelectedItem(),
				  name.getSelectionModel().getSelectedItem(),
				  timeSpinner.getEditor().getText(),
				  btnOnOff.buttonString(), a, b, c, d, e, f, g));

		}

	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("스케쥴 제거");
	}
}
