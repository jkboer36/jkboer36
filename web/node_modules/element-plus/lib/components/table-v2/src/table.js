'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
require('../../virtual-list/index.js');
var common = require('./common.js');
var row = require('./row.js');
var header = require('./header.js');
var grid = require('./grid.js');
var props = require('../../../utils/vue/props.js');
var props$1 = require('../../virtual-list/src/props.js');

const tableV2Props = props.buildProps({
  cache: grid.tableV2GridProps.cache,
  estimatedRowHeight: row.tableV2RowProps.estimatedRowHeight,
  rowKey: common.rowKey,
  cellProps: {
    type: props.definePropType([Object, Function])
  },
  headerClass: {
    type: props.definePropType([
      String,
      Function
    ])
  },
  headerProps: {
    type: props.definePropType([
      Object,
      Function
    ])
  },
  headerCellProps: {
    type: props.definePropType([
      Object,
      Function
    ])
  },
  headerHeight: header.tableV2HeaderProps.headerHeight,
  footerHeight: {
    type: Number,
    default: 0
  },
  rowClass: {
    type: props.definePropType([String, Function])
  },
  rowProps: {
    type: props.definePropType([Object, Function])
  },
  rowHeight: {
    type: Number,
    default: 50
  },
  columns: common.columns,
  data: common.dataType,
  dataGetter: {
    type: props.definePropType(Function)
  },
  dataKey: {
    type: String,
    default: "id"
  },
  fixedData: common.fixedDataType,
  expandColumnKey: row.tableV2RowProps.expandColumnKey,
  expandedRowKeys: common.expandKeys,
  defaultExpandedRowKeys: common.expandKeys,
  class: common.classType,
  disabled: Boolean,
  fixed: Boolean,
  style: {
    type: props.definePropType(Object)
  },
  width: common.requiredNumber,
  height: common.requiredNumber,
  maxHeight: Number,
  useIsScrolling: Boolean,
  indentSize: {
    type: Number,
    default: 12
  },
  iconSize: {
    type: Number,
    default: 12
  },
  hScrollbarSize: props$1.virtualizedGridProps.hScrollbarSize,
  vScrollbarSize: props$1.virtualizedGridProps.vScrollbarSize,
  scrollbarAlwaysOn: props$1.virtualizedScrollbarProps.alwaysOn,
  sortBy: {
    type: props.definePropType(Object),
    default: () => ({})
  },
  sortState: {
    type: props.definePropType(Object),
    default: void 0
  },
  onColumnSort: {
    type: props.definePropType(Function)
  },
  onExpandedRowsChange: Function,
  onEndReached: {
    type: props.definePropType(Function)
  },
  onRowExpand: row.tableV2RowProps.onRowExpand,
  onScroll: grid.tableV2GridProps.onScroll,
  onRowRendered: grid.tableV2GridProps.onRowsRendered,
  rowEventHandlers: row.tableV2RowProps.rowEventHandlers
});

exports.tableV2Props = tableV2Props;
//# sourceMappingURL=table.js.map
