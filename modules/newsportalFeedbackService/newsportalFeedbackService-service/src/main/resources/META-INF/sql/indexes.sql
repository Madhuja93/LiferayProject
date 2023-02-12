create index IX_1D61E1DA on FB_Feedback (companyId);
create index IX_CC0664D4 on FB_Feedback (feedbackText[$COLUMN_LENGTH:75$]);
create index IX_1B2D2E92 on FB_Feedback (groupId, companyId, feedbackText[$COLUMN_LENGTH:75$]);
create index IX_FD013802 on FB_Feedback (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4E1BD704 on FB_Feedback (uuid_[$COLUMN_LENGTH:75$], groupId);