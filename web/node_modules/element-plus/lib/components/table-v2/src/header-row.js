'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var common = require('./common.js');
var props = require('../../../utils/vue/props.js');

const tableV2HeaderRowProps = props.buildProps({
  class: String,
  columns: common.columns,
  headerIndex: Number,
  style: { type: props.definePropType(Object) }
});

exports.tableV2HeaderRowProps = tableV2HeaderRowProps;
//# sourceMappingURL=header-row.js.map
