import { defineComponent, getCurrentInstance, inject, computed, openBlock, createElementBlock, normalizeClass, createElementVNode, normalizeStyle, Fragment, createTextVNode, toDisplayString, renderSlot } from 'vue';
import '../../../utils/index.mjs';
import '../../../hooks/index.mjs';
import { menuItemGroupProps } from './menu-item-group.mjs';
import _export_sfc from '../../../_virtual/plugin-vue_export-helper.mjs';
import { throwError } from '../../../utils/error.mjs';
import { useNamespace } from '../../../hooks/use-namespace/index.mjs';

const COMPONENT_NAME = "ElMenuItemGroup";
const _sfc_main = defineComponent({
  name: COMPONENT_NAME,
  props: menuItemGroupProps,
  setup() {
    const instance = getCurrentInstance();
    const menu = inject("rootMenu");
    if (!menu)
      throwError(COMPONENT_NAME, "can not inject root menu");
    const ns = useNamespace("menu-item-group");
    const levelPadding = computed(() => {
      if (menu.props.collapse)
        return 20;
      let padding = 20;
      let parent = instance.parent;
      while (parent && parent.type.name !== "ElMenu") {
        if (parent.type.name === "ElSubMenu") {
          padding += 20;
        }
        parent = parent.parent;
      }
      return padding;
    });
    return {
      levelPadding,
      ns
    };
  }
});
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return openBlock(), createElementBlock("li", {
    class: normalizeClass(_ctx.ns.b())
  }, [
    createElementVNode("div", {
      class: normalizeClass(_ctx.ns.e("title")),
      style: normalizeStyle({ paddingLeft: `${_ctx.levelPadding}px` })
    }, [
      !_ctx.$slots.title ? (openBlock(), createElementBlock(Fragment, { key: 0 }, [
        createTextVNode(toDisplayString(_ctx.title), 1)
      ], 2112)) : renderSlot(_ctx.$slots, "title", { key: 1 })
    ], 6),
    createElementVNode("ul", null, [
      renderSlot(_ctx.$slots, "default")
    ])
  ], 2);
}
var MenuItemGroup = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-item-group.vue"]]);

export { MenuItemGroup as default };
//# sourceMappingURL=menu-item-group2.mjs.map
