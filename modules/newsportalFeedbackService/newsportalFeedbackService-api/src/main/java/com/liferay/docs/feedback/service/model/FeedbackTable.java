/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.feedback.service.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;FB_Feedback&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Feedback
 * @generated
 */
public class FeedbackTable extends BaseTable<FeedbackTable> {

	public static final FeedbackTable INSTANCE = new FeedbackTable();

	public final Column<FeedbackTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, Long> feedbackId = createColumn(
		"feedbackId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FeedbackTable, Date> feedbackDate = createColumn(
		"feedbackDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, String> feedbackText = createColumn(
		"feedbackText", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, String> feedbackSubject = createColumn(
		"feedbackSubject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FeedbackTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private FeedbackTable() {
		super("FB_Feedback", FeedbackTable::new);
	}

}