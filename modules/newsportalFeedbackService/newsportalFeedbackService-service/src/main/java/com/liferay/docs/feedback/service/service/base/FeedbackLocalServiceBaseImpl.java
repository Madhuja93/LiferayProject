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

package com.liferay.docs.feedback.service.service.base;

import com.liferay.docs.feedback.service.model.Feedback;
import com.liferay.docs.feedback.service.service.FeedbackLocalService;
import com.liferay.docs.feedback.service.service.FeedbackLocalServiceUtil;
import com.liferay.docs.feedback.service.service.persistence.FeedbackPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the feedback local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.docs.feedback.service.service.impl.FeedbackLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.docs.feedback.service.service.impl.FeedbackLocalServiceImpl
 * @generated
 */
public abstract class FeedbackLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FeedbackLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FeedbackLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FeedbackLocalServiceUtil</code>.
	 */

	/**
	 * Adds the feedback to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FeedbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param feedback the feedback
	 * @return the feedback that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Feedback addFeedback(Feedback feedback) {
		feedback.setNew(true);

		return feedbackPersistence.update(feedback);
	}

	/**
	 * Creates a new feedback with the primary key. Does not add the feedback to the database.
	 *
	 * @param feedbackId the primary key for the new feedback
	 * @return the new feedback
	 */
	@Override
	@Transactional(enabled = false)
	public Feedback createFeedback(long feedbackId) {
		return feedbackPersistence.create(feedbackId);
	}

	/**
	 * Deletes the feedback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FeedbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param feedbackId the primary key of the feedback
	 * @return the feedback that was removed
	 * @throws PortalException if a feedback with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Feedback deleteFeedback(long feedbackId) throws PortalException {
		return feedbackPersistence.remove(feedbackId);
	}

	/**
	 * Deletes the feedback from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FeedbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param feedback the feedback
	 * @return the feedback that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Feedback deleteFeedback(Feedback feedback) {
		return feedbackPersistence.remove(feedback);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return feedbackPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Feedback.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return feedbackPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.feedback.service.model.impl.FeedbackModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return feedbackPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.feedback.service.model.impl.FeedbackModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return feedbackPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return feedbackPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return feedbackPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Feedback fetchFeedback(long feedbackId) {
		return feedbackPersistence.fetchByPrimaryKey(feedbackId);
	}

	/**
	 * Returns the feedback matching the UUID and group.
	 *
	 * @param uuid the feedback's UUID
	 * @param groupId the primary key of the group
	 * @return the matching feedback, or <code>null</code> if a matching feedback could not be found
	 */
	@Override
	public Feedback fetchFeedbackByUuidAndGroupId(String uuid, long groupId) {
		return feedbackPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the feedback with the primary key.
	 *
	 * @param feedbackId the primary key of the feedback
	 * @return the feedback
	 * @throws PortalException if a feedback with the primary key could not be found
	 */
	@Override
	public Feedback getFeedback(long feedbackId) throws PortalException {
		return feedbackPersistence.findByPrimaryKey(feedbackId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(feedbackLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Feedback.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("feedbackId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			feedbackLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Feedback.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("feedbackId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(feedbackLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Feedback.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("feedbackId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return feedbackPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return feedbackLocalService.deleteFeedback((Feedback)persistedModel);
	}

	@Override
	public BasePersistence<Feedback> getBasePersistence() {
		return feedbackPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return feedbackPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the feedbacks matching the UUID and company.
	 *
	 * @param uuid the UUID of the feedbacks
	 * @param companyId the primary key of the company
	 * @return the matching feedbacks, or an empty list if no matches were found
	 */
	@Override
	public List<Feedback> getFeedbacksByUuidAndCompanyId(
		String uuid, long companyId) {

		return feedbackPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of feedbacks matching the UUID and company.
	 *
	 * @param uuid the UUID of the feedbacks
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of feedbacks
	 * @param end the upper bound of the range of feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching feedbacks, or an empty list if no matches were found
	 */
	@Override
	public List<Feedback> getFeedbacksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Feedback> orderByComparator) {

		return feedbackPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the feedback matching the UUID and group.
	 *
	 * @param uuid the feedback's UUID
	 * @param groupId the primary key of the group
	 * @return the matching feedback
	 * @throws PortalException if a matching feedback could not be found
	 */
	@Override
	public Feedback getFeedbackByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return feedbackPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.feedback.service.model.impl.FeedbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of feedbacks
	 * @param end the upper bound of the range of feedbacks (not inclusive)
	 * @return the range of feedbacks
	 */
	@Override
	public List<Feedback> getFeedbacks(int start, int end) {
		return feedbackPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of feedbacks.
	 *
	 * @return the number of feedbacks
	 */
	@Override
	public int getFeedbacksCount() {
		return feedbackPersistence.countAll();
	}

	/**
	 * Updates the feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FeedbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param feedback the feedback
	 * @return the feedback that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Feedback updateFeedback(Feedback feedback) {
		return feedbackPersistence.update(feedback);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FeedbackLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		feedbackLocalService = (FeedbackLocalService)aopProxy;

		_setLocalServiceUtilService(feedbackLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FeedbackLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Feedback.class;
	}

	protected String getModelClassName() {
		return Feedback.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = feedbackPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		FeedbackLocalService feedbackLocalService) {

		try {
			Field field = FeedbackLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, feedbackLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected FeedbackLocalService feedbackLocalService;

	@Reference
	protected FeedbackPersistence feedbackPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}