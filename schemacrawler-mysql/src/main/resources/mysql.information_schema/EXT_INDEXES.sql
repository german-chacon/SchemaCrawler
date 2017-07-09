SELECT DISTINCT
  INDEX_SCHEMA AS INDEX_CATALOG,
  NULL AS INDEX_SCHEMA,
  INDEX_NAME,
  TABLE_SCHEMA AS TABLE_CATALOG,
  NULL AS TABLE_SCHEMA,
  TABLE_NAME,
  INDEX_COMMENT AS REMARKS
FROM
  INFORMATION_SCHEMA.STATISTICS