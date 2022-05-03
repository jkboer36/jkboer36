'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var props = require('../../../utils/vue/props.js');
var typescript = require('../../../utils/typescript.js');

const classType = String;
const columns = {
  type: props.definePropType(Array),
  required: true
};
const column = {
  type: props.definePropType(Object)
};
const fixedDataType = {
  type: props.definePropType(Array)
};
const dataType = {
  ...fixedDataType,
  required: true
};
const expandColumnKey = String;
const expandKeys = {
  type: props.definePropType(Array),
  default: () => typescript.mutable([])
};
const requiredNumber = {
  type: Number,
  required: true
};
const rowKey = {
  type: props.definePropType([String, Number, Symbol]),
  default: "id"
};
const styleType = {
  type: props.definePropType(Object)
};

exports.classType = classType;
exports.column = column;
exports.columns = columns;
exports.dataType = dataType;
exports.expandColumnKey = expandColumnKey;
exports.expandKeys = expandKeys;
exports.fixedDataType = fixedDataType;
exports.requiredNumber = requiredNumber;
exports.rowKey = rowKey;
exports.styleType = styleType;
//# sourceMappingURL=common.js.map
