'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
require('../../virtual-list/index.js');
var common = require('./common.js');
var props = require('../../../utils/vue/props.js');
var props$1 = require('../../virtual-list/src/props.js');

const tableV2RowProps = props.buildProps({
  class: String,
  columns: common.columns,
  depth: Number,
  expandColumnKey: common.expandColumnKey,
  estimatedRowHeight: {
    ...props$1.virtualizedGridProps.estimatedRowHeight,
    default: void 0
  },
  isScrolling: Boolean,
  onRowExpand: {
    type: props.definePropType(Function)
  },
  onRowHover: {
    type: props.definePropType(Function)
  },
  onRowHeightChange: {
    type: props.definePropType(Function)
  },
  rowData: {
    type: props.definePropType(Object),
    required: true
  },
  rowEventHandlers: {
    type: props.definePropType(Object)
  },
  rowIndex: {
    type: Number,
    required: true
  },
  rowKey: common.rowKey,
  style: {
    type: props.definePropType(Object)
  }
});

exports.tableV2RowProps = tableV2RowProps;
//# sourceMappingURL=row.js.map
