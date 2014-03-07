package org.irdresearch.tbreach.mobile.util;

import javax.microedition.lcdui.List;

import org.irdresearch.tbreach.mobile.model.ListItem;

public class MenuUtil {

	public static void addListItems(ListItem[] items, List list) {
		list.deleteAll();
		for (int i = 0; i < items.length; i++) {
			if (items[i].isShow()) {
				list.append(items[i].getDisplayName(), null);
			}
		}
	}
}
