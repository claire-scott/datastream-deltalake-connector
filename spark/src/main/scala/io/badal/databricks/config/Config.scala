package io.badal.databricks.config

import eu.timepit.refined.types.string.NonEmptyString
import eu.timepit.refined.types.numeric.PosInt

object Config {

  final case class DatastreamJobConf(
      datastream: DatastreamConf,
      tables: Seq[TableConf],
      deltalake: DeltalakeConf
  ) {
    def path(table: String): String =
      s"${datastream.bucket.value}/${datastream.database.value}.$table"
  }

  final case class DatastreamConf(
      name: NonEmptyString,
      bucket: NonEmptyString,
      database: NonEmptyString,
      startDate: Option[NonEmptyString],
      fileReadConcurrency: PosInt
  )

  final case class TableConf(name: NonEmptyString,
                             primaryKey: NonEmptyString,
                             timestamp: NonEmptyString)

  final case class DeltalakeConf(tableNamePrefix: String,
                                 mergeFrequencyMinutes: PosInt)

}