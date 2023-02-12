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

package com.liferay.docs.feedback.service.model.impl;

import com.liferay.docs.feedback.service.model.Feedback;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Feedback in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FeedbackCacheModel
	implements CacheModel<Feedback>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FeedbackCacheModel)) {
			return false;
		}

		FeedbackCacheModel feedbackCacheModel = (FeedbackCacheModel)object;

		if (feedbackId == feedbackCacheModel.feedbackId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, feedbackId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", feedbackId=");
		sb.append(feedbackId);
		sb.append(", feedbackDate=");
		sb.append(feedbackDate);
		sb.append(", feedbackText=");
		sb.append(feedbackText);
		sb.append(", feedbackSubject=");
		sb.append(feedbackSubject);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Feedback toEntityModel() {
		FeedbackImpl feedbackImpl = new FeedbackImpl();

		if (uuid == null) {
			feedbackImpl.setUuid("");
		}
		else {
			feedbackImpl.setUuid(uuid);
		}

		feedbackImpl.setFeedbackId(feedbackId);

		if (feedbackDate == Long.MIN_VALUE) {
			feedbackImpl.setFeedbackDate(null);
		}
		else {
			feedbackImpl.setFeedbackDate(new Date(feedbackDate));
		}

		if (feedbackText == null) {
			feedbackImpl.setFeedbackText("");
		}
		else {
			feedbackImpl.setFeedbackText(feedbackText);
		}

		if (feedbackSubject == null) {
			feedbackImpl.setFeedbackSubject("");
		}
		else {
			feedbackImpl.setFeedbackSubject(feedbackSubject);
		}

		feedbackImpl.setCompanyId(companyId);
		feedbackImpl.setGroupId(groupId);
		feedbackImpl.setUserId(userId);

		feedbackImpl.resetOriginalValues();

		return feedbackImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		feedbackId = objectInput.readLong();
		feedbackDate = objectInput.readLong();
		feedbackText = objectInput.readUTF();
		feedbackSubject = objectInput.readUTF();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(feedbackId);
		objectOutput.writeLong(feedbackDate);

		if (feedbackText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackText);
		}

		if (feedbackSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackSubject);
		}

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
	}

	public String uuid;
	public long feedbackId;
	public long feedbackDate;
	public String feedbackText;
	public String feedbackSubject;
	public long companyId;
	public long groupId;
	public long userId;

}