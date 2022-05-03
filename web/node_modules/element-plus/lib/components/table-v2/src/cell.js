'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var common = require('./common.js');
var props = require('../../../utils/vue/props.js');

const tableV2CellProps = props.buildProps({
  class: String,
  cellData: {
    type: props.definePropType([String, Boolean, Number, Object])
  },
  column: common.column,
  columnIndex: Number,
  style: {
    type: props.definePropType([String, Array, Object])
  },
  rowData: {
    type: props.definePropType(Object)
  },
  rowIndex: Number
});

exports.tableV2CellProps = tableV2CellProps;
//# sourceMappingURL=cell.js.map
