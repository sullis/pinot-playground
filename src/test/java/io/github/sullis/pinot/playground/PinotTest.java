package io.github.sullis.pinot.playground;

import java.net.URI;
import java.nio.file.Files;
import org.apache.pinot.spi.config.table.TableConfig;
import org.apache.pinot.spi.config.table.TableType;
import org.apache.pinot.spi.data.DimensionFieldSpec;
import org.apache.pinot.spi.data.FieldSpec.DataType;
import org.apache.pinot.spi.data.MetricFieldSpec;
import org.apache.pinot.spi.data.Schema;
import org.apache.pinot.spi.filesystem.LocalPinotFS;
import org.apache.pinot.spi.utils.builder.TableConfigBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PinotTest {

  private static final String TABLE_NAME = "myTable";

  @Test
  void schemaWithDimensionsAndMetrics() {
    Schema schema = new Schema.SchemaBuilder()
        .setSchemaName(TABLE_NAME)
        .addSingleValueDimension("userId", DataType.LONG)
        .addSingleValueDimension("country", DataType.STRING)
        .addMetric("revenue", DataType.DOUBLE)
        .addMetric("clickCount", DataType.INT)
        .addDateTime("eventTime", DataType.LONG, "1:MILLISECONDS:EPOCH", "1:MILLISECONDS")
        .build();

    assertThat(schema.getSchemaName()).isEqualTo(TABLE_NAME);
    assertThat(schema.getDimensionFieldSpecs()).hasSize(2);
    assertThat(schema.getMetricFieldSpecs()).hasSize(2);
    assertThat(schema.getDateTimeFieldSpecs()).hasSize(1);
    assertThat(schema.getFieldSpecMap()).containsKeys("userId", "country", "revenue", "clickCount", "eventTime");
  }

  @Test
  void dimensionFieldSpec() {
    DimensionFieldSpec spec = new DimensionFieldSpec("region", DataType.STRING, true);
    assertThat(spec.getName()).isEqualTo("region");
    assertThat(spec.getDataType()).isEqualTo(DataType.STRING);
    assertThat(spec.isSingleValueField()).isTrue();
  }

  @Test
  void metricFieldSpec() {
    MetricFieldSpec spec = new MetricFieldSpec("pageViews", DataType.LONG);
    assertThat(spec.getName()).isEqualTo("pageViews");
    assertThat(spec.getDataType()).isEqualTo(DataType.LONG);
  }

  @Test
  void offlineTableConfig() {
    TableConfig tableConfig = new TableConfigBuilder(TableType.OFFLINE)
        .setTableName(TABLE_NAME)
        .setNumReplicas(3)
        .build();

    assertThat(tableConfig.getTableName()).isEqualTo(TABLE_NAME + "_OFFLINE");
    assertThat(tableConfig.getTableType()).isEqualTo(TableType.OFFLINE);
    assertThat(tableConfig.getReplication()).isEqualTo(3);
  }

  @Test
  void realtimeTableConfig() {
    TableConfig tableConfig = new TableConfigBuilder(TableType.REALTIME)
        .setTableName(TABLE_NAME)
        .setNumReplicas(2)
        .build();

    assertThat(tableConfig.getTableName()).isEqualTo(TABLE_NAME + "_REALTIME");
    assertThat(tableConfig.getTableType()).isEqualTo(TableType.REALTIME);
    assertThat(tableConfig.getReplication()).isEqualTo(2);
  }

  @ParameterizedTest
  @EnumSource(value = DataType.class, names = {"INT", "LONG", "FLOAT", "DOUBLE", "STRING"})
  void metricDataTypes(DataType dataType) {
    MetricFieldSpec spec = new MetricFieldSpec("value", dataType);
    assertThat(spec.getDataType()).isEqualTo(dataType);
  }

  @Test
  void localPinotFSOperations() throws Exception {
    LocalPinotFS fs = new LocalPinotFS();
    URI tmpDir = Files.createTempDirectory("pinot-test-").toUri();

    assertThat(fs.exists(tmpDir)).isTrue();
    assertThat(fs.isDirectory(tmpDir)).isTrue();
    assertThat(fs.listFiles(tmpDir, false)).isEmpty();

    URI subDir = tmpDir.resolve("subdir/");
    assertThat(fs.mkdir(subDir)).isTrue();
    assertThat(fs.exists(subDir)).isTrue();

    fs.delete(subDir, false);
    assertThat(fs.exists(subDir)).isFalse();

    fs.delete(tmpDir, true);
  }
}
