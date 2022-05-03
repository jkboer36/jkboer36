'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var common = require('./common.js');
var props = require('../../../utils/vue/props.js');

const tableV2HeaderCell = props.buildProps({
  class: common.classType,
  columnIndex: Number,
  column: common.column
});

exports.tableV2HeaderCell = tableV2HeaderCell;
//# sourceMappingURL=header-cell.js.map
