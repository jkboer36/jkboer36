import '../../../utils/index.mjs';
import '../../virtual-list/index.mjs';
import { columns, expandColumnKey, rowKey } from './common.mjs';
import { buildProps, definePropType } from '../../../utils/vue/props.mjs';
import { virtualizedGridProps } from '../../virtual-list/src/props.mjs';

const tableV2RowProps = buildProps({
  class: String,
  columns,
  depth: Number,
  expandColumnKey,
  estimatedRowHeight: {
    ...virtualizedGridProps.estimatedRowHeight,
    default: void 0
  },
  isScrolling: Boolean,
  onRowExpand: {
    type: definePropType(Function)
  },
  onRowHover: {
    type: definePropType(Function)
  },
  onRowHeightChange: {
    type: definePropType(Function)
  },
  rowData: {
    type: definePropType(Object),
    required: true
  },
  rowEventHandlers: {
    type: definePropType(Object)
  },
  rowIndex: {
    type: Number,
    required: true
  },
  rowKey,
  style: {
    type: definePropType(Object)
  }
});

export { tableV2RowProps };
//# sourceMappingURL=row.mjs.map
