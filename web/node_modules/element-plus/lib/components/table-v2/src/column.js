'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var props = require('../../../utils/vue/props.js');

const widthType = {
  type: Number,
  default: void 0
};
const tableV2ColumnProps = props.buildProps({
  align: {
    type: props.definePropType(String),
    default: "left"
  },
  class: String,
  fixed: {
    type: props.definePropType([String, Boolean]),
    default: false
  },
  headerClass: String,
  hidden: Boolean,
  resizable: Boolean,
  style: {
    type: props.definePropType(Object)
  },
  sortable: Boolean,
  title: String,
  maxWidth: widthType,
  minWidth: widthType,
  width: {
    type: Number,
    required: true
  },
  cellRenderer: {
    type: props.definePropType([Function, Object])
  },
  headerRenderer: {
    type: props.definePropType([Function, Object])
  }
});

exports.tableV2ColumnProps = tableV2ColumnProps;
//# sourceMappingURL=column.js.map
