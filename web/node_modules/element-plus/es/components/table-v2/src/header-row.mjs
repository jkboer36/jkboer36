import '../../../utils/index.mjs';
import { columns } from './common.mjs';
import { buildProps, definePropType } from '../../../utils/vue/props.mjs';

const tableV2HeaderRowProps = buildProps({
  class: String,
  columns,
  headerIndex: Number,
  style: { type: definePropType(Object) }
});

export { tableV2HeaderRowProps };
//# sourceMappingURL=header-row.mjs.map
