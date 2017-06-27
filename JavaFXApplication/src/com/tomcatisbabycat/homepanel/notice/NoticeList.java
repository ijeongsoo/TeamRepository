/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.notice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kang
 */
public class NoticeList {
	private ObservableList<Memo> memoList = FXCollections.observableArrayList();
	
	private static NoticeList noticeList = new NoticeList();
	
	private NoticeList(){
		
	}
	
	public static NoticeList getInstance(){
		return noticeList;
	}

	public ObservableList<Memo> getMemoList() {
		return memoList;
	}

	public void setMemoList(ObservableList<Memo> memoList) {
		this.memoList = memoList;
	}
	
	
}
