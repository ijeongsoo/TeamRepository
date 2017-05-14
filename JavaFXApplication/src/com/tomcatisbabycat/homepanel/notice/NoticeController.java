/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.notice;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class NoticeController implements Initializable {
	@FXML
	private StackPane noticeStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
	@FXML
	private Button btnControlAdd;
	@FXML
	private TableView<Memo> noticeTableView;

	@FXML
	private Rectangle menuBack;

	@FXML
	private TextArea addTextArea;
	private String writeDate;
	private ObservableList<Memo> list;
	@FXML
	private Button btnControlDelete;

	private TextArea regTextArea;

	private NoticeList noticeList = NoticeList.getInstance();

	Parent parent;
	@FXML
	private DatePicker dataPick;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlAdd.setFocusTraversable(false);
		btnControlBack.setFocusTraversable(false);
		btnControlDelete.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);

		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event -> {
			handleBtnControlBack(event);
		});
		btnControlAdd.setOnAction(event -> {
			handleBtnControlAdd(event);
		});
		btnControlDelete.setOnAction(event -> {
			handleBtnControlDelete(event);
		});

//            noticeTableView.getFocusModel().getFocusedItem().setContents(editTextArea.getText());
//            noticeTableView.getFocusModel().getFocusedItem().setDate(regYearCombo.getValue() + regMonthCombo.getValue() + regDayCombo.getValue());
            //dataPick의 default값 셋팅
            dataPick.setValue(LocalDate.now());

            //TableView 셋팅
            TableColumn tc0 = noticeTableView.getColumns().get(0);
            TableColumn tc1 = noticeTableView.getColumns().get(1);   //각 열의 속성을 받아냄
            tc0.setCellValueFactory(new PropertyValueFactory<Memo, String>("contents"));   //각 열의 속성을 String으로 구성된 memo객체로 생성
            tc1.setCellValueFactory(new PropertyValueFactory<Memo, String>("date"));
//            ObservableList<Memo> list = FXCollections.observableArrayList();
//            this.list = list;
            this.list = noticeList.getMemoList();
            noticeTableView.setItems(list);

            //TableView 내용 편집
            noticeTableView.setEditable(true);
            tc0.setCellFactory(TextFieldTableCell.forTableColumn());
            tc0.setOnEditCommit(
                    new EventHandler<CellEditEvent<Memo, String>>() {
                  @Override
                  public void handle(CellEditEvent<Memo, String> t) {

                        ((Memo) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContents(t.getNewValue());
                  }
            }
            );
      }

      private void handleBtnControlAdd(ActionEvent event) {
            if (addTextArea.getText().trim().isEmpty()) {
                 //  || dataPick.getValue() == null  //default값을 설정해두었으므로 조건에서 빠져도 무방.
                  Stage primaryStage = (Stage) btnControlAdd.getScene().getWindow();
                  Popup popup = new Popup();
                  try {
                        parent = FXMLLoader.load(getClass().getResource("addPopup.fxml"));
                  } catch (IOException ex) {
                        Logger.getLogger(NoticeController.class.getName()).log(Level.SEVERE, null, ex);
                  }

                  popup.getContent().add(parent);
                  popup.setAutoHide(true);
                  popup.show(primaryStage);   //textArea에 입력내용없을시 팝업을 띄움

                  System.out.println("입력내용이 없습니다.");

            } else {
                  //System.out.println(dataPick.getValue().toString());
                  noticeList.getMemoList().add(new Memo(addTextArea.getText(), dataPick.getValue().toString()));  //새로운 메모객체 생성하여 리스트에 추가
                  noticeTableView.setItems(noticeList.getMemoList());  // 메모객체를 담은 리스트를 tableView에 올림.
                  addTextArea.clear();
            }

      }

      private void handleBtnControlDelete(ActionEvent event) {

            int selectedIndex = noticeTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                  Stage primaryStage = (Stage) btnControlAdd.getScene().getWindow();
                  Popup popup = new Popup();
                  try {
                        parent = FXMLLoader.load(getClass().getResource("deletePopup.fxml"));
                  } catch (IOException ex) {
                  }

                  popup.getContent().add(parent);
                  popup.setAutoHide(true);
                  popup.show(primaryStage);   //삭제할 항목을 선택하지 않으면 팝업이 뜸.
                  System.out.println("삭제할 메모를 선택해주세요.");
            } else {
                  noticeList.getMemoList().remove(selectedIndex);
            }

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
            //StackPane rootPane = (StackPane) noticeStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

           LockController.lockRootPane.getChildren().remove(noticeStackPane);
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

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Notice 제거"); //To change body of generated methods, choose Tools | Templates.
	}

	
}
