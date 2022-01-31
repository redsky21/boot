var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = exports.Wrapper = void 0;

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _Button = require("./Button.helpers");

var _OverlaySpinner = _interopRequireDefault(require("../OverlaySpinner"));

var _ButtonBase = _interopRequireDefault(require("@mui/material/ButtonBase"));

var _excluded = ["type", "children", "variant", "color", "size", "isLoading", "disabled", "fullWidth", "ButtonBaseProps"];

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

var Button = /*#__PURE__*/_react["default"].forwardRef(function (_ref, ref) {
  var _ref$type = _ref.type,
      type = _ref$type === void 0 ? 'button' : _ref$type,
      children = _ref.children,
      _ref$variant = _ref.variant,
      variant = _ref$variant === void 0 ? 'contained' : _ref$variant,
      _ref$color = _ref.color,
      color = _ref$color === void 0 ? 'primary' : _ref$color,
      _ref$size = _ref.size,
      size = _ref$size === void 0 ? 'md' : _ref$size,
      _ref$isLoading = _ref.isLoading,
      isLoading = _ref$isLoading === void 0 ? false : _ref$isLoading,
      _ref$disabled = _ref.disabled,
      disabled = _ref$disabled === void 0 ? false : _ref$disabled,
      _ref$fullWidth = _ref.fullWidth,
      fullWidth = _ref$fullWidth === void 0 ? false : _ref$fullWidth,
      ButtonBaseProps = _ref.ButtonBaseProps,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_ButtonBase["default"], (0, _extends2["default"])({
    type: type,
    disabled: disabled,
    sx: {
      width: fullWidth ? '100%' : 'auto',
      minWidth: 'unset !important',
      height: 'auto !important',
      padding: '0 !important'
    }
  }, ButtonBaseProps), /*#__PURE__*/_react["default"].createElement(Wrapper, (0, _extends2["default"])({
    ref: ref,
    role: "button",
    variant: variant,
    color: color,
    size: size,
    disabled: disabled,
    fullWidth: fullWidth
  }, rest), isLoading && /*#__PURE__*/_react["default"].createElement(_OverlaySpinner["default"], null), /*#__PURE__*/_react["default"].createElement(Inner, {
    style: {
      opacity: isLoading ? 0 : 1
    }
  }, children)));
});

var _default = Button;
exports["default"] = _default;
var Wrapper = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "ejl4zhq1"
} : {
  target: "ejl4zhq1",
  label: "Wrapper"
})("position:relative;appearance:none;display:inline-flex;align-items:center;border:0;outline:0;background-color:inherit;transition:all 0.125s ease-out;cursor:pointer;padding:1.0rem 2.2rem;border-radius:0.6rem;font-size:1.4rem;", function (_ref2) {
  var variant = _ref2.variant,
      color = _ref2.color,
      size = _ref2.size,
      disabled = _ref2.disabled,
      fullWidth = _ref2.fullWidth;
  return _objectSpread(_objectSpread(_objectSpread(_objectSpread({}, size === 'inherit' && {
    height: 'auto',
    lineHeight: 'inherit',
    padding: 0
  }), fullWidth && {
    width: '100%'
  }), (0, _Button.generateButtonColorStyles)({
    variant: variant,
    color: color
  })), {}, {
    // disabled
    '&[disabled]': {
      // 기본
      opacity: 0.8,
      cursor: 'not-allowed',
      // red theme
      '.theme-dark &': {
        opacity: 0.5
      }
    }
  });
}, process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0J1dHRvbi9CdXR0b24udHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQW1FdUIiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvQnV0dG9uL0J1dHRvbi50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCB7IGdlbmVyYXRlQnV0dG9uQ29sb3JTdHlsZXMgfSBmcm9tICcuL0J1dHRvbi5oZWxwZXJzJztcclxuaW1wb3J0IE92ZXJsYXlTcGlubmVyIGZyb20gJy4uL092ZXJsYXlTcGlubmVyJztcclxuaW1wb3J0IEJ1dHRvbkJhc2UsIHsgQnV0dG9uQmFzZVByb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9CdXR0b25CYXNlJztcclxuXHJcbmV4cG9ydCB0eXBlIFRCdXR0b25Qcm9wcyA9IHtcclxuICB2YXJpYW50PzogJ25vbmUnIHwgJ291dGxpbmVkJyB8ICdjb250YWluZWQnO1xyXG4gIGNvbG9yPzogJ3ByaW1hcnknIHwgJ3ByaW1hcnktcmV2ZXJzZScgfCAnbm9ybWFsJyB8ICdpbmhlcml0JztcclxuICBzaXplPzogJ3hzJyB8ICdzbScgfCAnbWQnIHwgJ2xnJyB8ICd4bCcgfCAnaW5oZXJpdCc7XHJcbiAgaXNMb2FkaW5nPzogYm9vbGVhbjtcclxuICBkaXNhYmxlZD86IGJvb2xlYW47XHJcbiAgZnVsbFdpZHRoPzogYm9vbGVhbjtcclxuICBhcz86IFJlYWN0LkVsZW1lbnRUeXBlPGFueT4gfCB1bmRlZmluZWQ7XHJcbiAgdG8/OiBzdHJpbmc7XHJcbiAgdHlwZT86ICdidXR0b24nIHwgJ3N1Ym1pdCc7XHJcbiAgQnV0dG9uQmFzZVByb3BzPzogQnV0dG9uQmFzZVByb3BzO1xyXG59ICYgUmVhY3QuSFRNTEF0dHJpYnV0ZXM8SFRNTERpdkVsZW1lbnQ+O1xyXG5cclxuY29uc3QgQnV0dG9uID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVEJ1dHRvblByb3BzPihcclxuICAoXHJcbiAgICB7XHJcbiAgICAgIHR5cGUgPSAnYnV0dG9uJyxcclxuICAgICAgY2hpbGRyZW4sXHJcbiAgICAgIHZhcmlhbnQgPSAnY29udGFpbmVkJyxcclxuICAgICAgY29sb3IgPSAncHJpbWFyeScsXHJcbiAgICAgIHNpemUgPSAnbWQnLFxyXG4gICAgICBpc0xvYWRpbmcgPSBmYWxzZSxcclxuICAgICAgZGlzYWJsZWQgPSBmYWxzZSxcclxuICAgICAgZnVsbFdpZHRoID0gZmFsc2UsXHJcbiAgICAgIEJ1dHRvbkJhc2VQcm9wcyxcclxuICAgICAgLi4ucmVzdFxyXG4gICAgfSxcclxuICAgIHJlZixcclxuICApID0+IHtcclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxCdXR0b25CYXNlXHJcbiAgICAgICAgdHlwZT17dHlwZX1cclxuICAgICAgICBkaXNhYmxlZD17ZGlzYWJsZWR9XHJcbiAgICAgICAgc3g9e3tcclxuICAgICAgICAgIHdpZHRoOiBmdWxsV2lkdGggPyAnMTAwJScgOiAnYXV0bycsXHJcbiAgICAgICAgICBtaW5XaWR0aDogJ3Vuc2V0ICFpbXBvcnRhbnQnLFxyXG4gICAgICAgICAgaGVpZ2h0OiAnYXV0byAhaW1wb3J0YW50JyxcclxuICAgICAgICAgIHBhZGRpbmc6ICcwICFpbXBvcnRhbnQnLFxyXG4gICAgICAgIH19XHJcbiAgICAgICAgey4uLkJ1dHRvbkJhc2VQcm9wc31cclxuICAgICAgPlxyXG4gICAgICAgIDxXcmFwcGVyXHJcbiAgICAgICAgICByZWY9e3JlZn1cclxuICAgICAgICAgIHJvbGU9XCJidXR0b25cIlxyXG4gICAgICAgICAgdmFyaWFudD17dmFyaWFudH1cclxuICAgICAgICAgIGNvbG9yPXtjb2xvcn1cclxuICAgICAgICAgIHNpemU9e3NpemV9XHJcbiAgICAgICAgICBkaXNhYmxlZD17ZGlzYWJsZWR9XHJcbiAgICAgICAgICBmdWxsV2lkdGg9e2Z1bGxXaWR0aH1cclxuICAgICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAgID5cclxuICAgICAgICAgIHtpc0xvYWRpbmcgJiYgPE92ZXJsYXlTcGlubmVyIC8+fVxyXG4gICAgICAgICAgPElubmVyIHN0eWxlPXt7IG9wYWNpdHk6IGlzTG9hZGluZyA/IDAgOiAxIH19PntjaGlsZHJlbn08L0lubmVyPlxyXG4gICAgICAgIDwvV3JhcHBlcj5cclxuICAgICAgPC9CdXR0b25CYXNlPlxyXG4gICAgKTtcclxuICB9LFxyXG4pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgQnV0dG9uO1xyXG5cclxuZXhwb3J0IGNvbnN0IFdyYXBwZXIgPSBzdHlsZWQuZGl2PFRCdXR0b25Qcm9wcz4oXHJcbiAge1xyXG4gICAgcG9zaXRpb246ICdyZWxhdGl2ZScsXHJcbiAgICBhcHBlYXJhbmNlOiAnbm9uZScsXHJcbiAgICBkaXNwbGF5OiAnaW5saW5lLWZsZXgnLFxyXG4gICAgYWxpZ25JdGVtczogJ2NlbnRlcicsXHJcbiAgICBib3JkZXI6IDAsXHJcbiAgICBvdXRsaW5lOiAwLFxyXG4gICAgYmFja2dyb3VuZENvbG9yOiAnaW5oZXJpdCcsXHJcbiAgICB0cmFuc2l0aW9uOiAnYWxsIDAuMTI1cyBlYXNlLW91dCcsXHJcbiAgICBjdXJzb3I6ICdwb2ludGVyJyxcclxuICAgIHBhZGRpbmc6ICcxLjByZW0gMi4ycmVtJyxcclxuICAgIGJvcmRlclJhZGl1czogJzAuNnJlbScsXHJcbiAgICBmb250U2l6ZTogJzEuNHJlbScsXHJcbiAgICAvLyAnJjpob3Zlcic6IHtcclxuICAgIC8vICAgdHJhbnNmb3JtOiAndHJhbnNsYXRlM2QoMCwgLTFweCwgMCknLFxyXG4gICAgLy8gfSxcclxuICAgIC8vICcmOmFjdGl2ZSc6IHtcclxuICAgIC8vICAgdHJhbnNmb3JtOiAndHJhbnNsYXRlM2QoMCwgMCwgMCknLFxyXG4gICAgLy8gfSxcclxuICB9LFxyXG4gICh7IHZhcmlhbnQsIGNvbG9yLCBzaXplLCBkaXNhYmxlZCwgZnVsbFdpZHRoIH0pID0+ICh7XHJcbiAgICAuLi4oc2l6ZSA9PT0gJ2luaGVyaXQnICYmIHtcclxuICAgICAgaGVpZ2h0OiAnYXV0bycsXHJcbiAgICAgIGxpbmVIZWlnaHQ6ICdpbmhlcml0JyxcclxuICAgICAgcGFkZGluZzogMCxcclxuICAgIH0pLFxyXG4gICAgLi4uKGZ1bGxXaWR0aCAmJiB7XHJcbiAgICAgIHdpZHRoOiAnMTAwJScsXHJcbiAgICB9KSxcclxuXHJcbiAgICAvLyAuLi5nZW5lcmF0ZUJ1dHRvblNpemVTdHlsZXMoeyBzaXplIH0pLFxyXG4gICAgLi4uZ2VuZXJhdGVCdXR0b25Db2xvclN0eWxlcyh7IHZhcmlhbnQsIGNvbG9yIH0pLFxyXG5cclxuICAgIC8vIGRpc2FibGVkXHJcbiAgICAnJltkaXNhYmxlZF0nOiB7XHJcbiAgICAgIC8vIOq4sOuzuFxyXG4gICAgICBvcGFjaXR5OiAwLjgsXHJcbiAgICAgIGN1cnNvcjogJ25vdC1hbGxvd2VkJyxcclxuXHJcbiAgICAgIC8vIHJlZCB0aGVtZVxyXG4gICAgICAnLnRoZW1lLWRhcmsgJic6IHtcclxuICAgICAgICBvcGFjaXR5OiAwLjUsXHJcbiAgICAgIH0sXHJcbiAgICB9LFxyXG4gIH0pLFxyXG4pO1xyXG5cclxuY29uc3QgSW5uZXIgPSBzdHlsZWQuZGl2YFxyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgbGluZS1oZWlnaHQ6IDE7XHJcbiAgY29sb3I6IGN1cnJlbnRDb2xvcjtcclxuICBmb250LXNpemU6IDFlbTtcclxuYDtcclxuIl19 */");
exports.Wrapper = Wrapper;
var Inner = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "ejl4zhq0"
} : {
  target: "ejl4zhq0",
  label: "Inner"
})(process.env.NODE_ENV === "production" ? {
  name: "1d886sm",
  styles: "display:flex;line-height:1;color:currentColor;font-size:1em"
} : {
  name: "1d886sm",
  styles: "display:flex;line-height:1;color:currentColor;font-size:1em",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0J1dHRvbi9CdXR0b24udHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQW1Id0IiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvQnV0dG9uL0J1dHRvbi50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCB7IGdlbmVyYXRlQnV0dG9uQ29sb3JTdHlsZXMgfSBmcm9tICcuL0J1dHRvbi5oZWxwZXJzJztcclxuaW1wb3J0IE92ZXJsYXlTcGlubmVyIGZyb20gJy4uL092ZXJsYXlTcGlubmVyJztcclxuaW1wb3J0IEJ1dHRvbkJhc2UsIHsgQnV0dG9uQmFzZVByb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9CdXR0b25CYXNlJztcclxuXHJcbmV4cG9ydCB0eXBlIFRCdXR0b25Qcm9wcyA9IHtcclxuICB2YXJpYW50PzogJ25vbmUnIHwgJ291dGxpbmVkJyB8ICdjb250YWluZWQnO1xyXG4gIGNvbG9yPzogJ3ByaW1hcnknIHwgJ3ByaW1hcnktcmV2ZXJzZScgfCAnbm9ybWFsJyB8ICdpbmhlcml0JztcclxuICBzaXplPzogJ3hzJyB8ICdzbScgfCAnbWQnIHwgJ2xnJyB8ICd4bCcgfCAnaW5oZXJpdCc7XHJcbiAgaXNMb2FkaW5nPzogYm9vbGVhbjtcclxuICBkaXNhYmxlZD86IGJvb2xlYW47XHJcbiAgZnVsbFdpZHRoPzogYm9vbGVhbjtcclxuICBhcz86IFJlYWN0LkVsZW1lbnRUeXBlPGFueT4gfCB1bmRlZmluZWQ7XHJcbiAgdG8/OiBzdHJpbmc7XHJcbiAgdHlwZT86ICdidXR0b24nIHwgJ3N1Ym1pdCc7XHJcbiAgQnV0dG9uQmFzZVByb3BzPzogQnV0dG9uQmFzZVByb3BzO1xyXG59ICYgUmVhY3QuSFRNTEF0dHJpYnV0ZXM8SFRNTERpdkVsZW1lbnQ+O1xyXG5cclxuY29uc3QgQnV0dG9uID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVEJ1dHRvblByb3BzPihcclxuICAoXHJcbiAgICB7XHJcbiAgICAgIHR5cGUgPSAnYnV0dG9uJyxcclxuICAgICAgY2hpbGRyZW4sXHJcbiAgICAgIHZhcmlhbnQgPSAnY29udGFpbmVkJyxcclxuICAgICAgY29sb3IgPSAncHJpbWFyeScsXHJcbiAgICAgIHNpemUgPSAnbWQnLFxyXG4gICAgICBpc0xvYWRpbmcgPSBmYWxzZSxcclxuICAgICAgZGlzYWJsZWQgPSBmYWxzZSxcclxuICAgICAgZnVsbFdpZHRoID0gZmFsc2UsXHJcbiAgICAgIEJ1dHRvbkJhc2VQcm9wcyxcclxuICAgICAgLi4ucmVzdFxyXG4gICAgfSxcclxuICAgIHJlZixcclxuICApID0+IHtcclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxCdXR0b25CYXNlXHJcbiAgICAgICAgdHlwZT17dHlwZX1cclxuICAgICAgICBkaXNhYmxlZD17ZGlzYWJsZWR9XHJcbiAgICAgICAgc3g9e3tcclxuICAgICAgICAgIHdpZHRoOiBmdWxsV2lkdGggPyAnMTAwJScgOiAnYXV0bycsXHJcbiAgICAgICAgICBtaW5XaWR0aDogJ3Vuc2V0ICFpbXBvcnRhbnQnLFxyXG4gICAgICAgICAgaGVpZ2h0OiAnYXV0byAhaW1wb3J0YW50JyxcclxuICAgICAgICAgIHBhZGRpbmc6ICcwICFpbXBvcnRhbnQnLFxyXG4gICAgICAgIH19XHJcbiAgICAgICAgey4uLkJ1dHRvbkJhc2VQcm9wc31cclxuICAgICAgPlxyXG4gICAgICAgIDxXcmFwcGVyXHJcbiAgICAgICAgICByZWY9e3JlZn1cclxuICAgICAgICAgIHJvbGU9XCJidXR0b25cIlxyXG4gICAgICAgICAgdmFyaWFudD17dmFyaWFudH1cclxuICAgICAgICAgIGNvbG9yPXtjb2xvcn1cclxuICAgICAgICAgIHNpemU9e3NpemV9XHJcbiAgICAgICAgICBkaXNhYmxlZD17ZGlzYWJsZWR9XHJcbiAgICAgICAgICBmdWxsV2lkdGg9e2Z1bGxXaWR0aH1cclxuICAgICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAgID5cclxuICAgICAgICAgIHtpc0xvYWRpbmcgJiYgPE92ZXJsYXlTcGlubmVyIC8+fVxyXG4gICAgICAgICAgPElubmVyIHN0eWxlPXt7IG9wYWNpdHk6IGlzTG9hZGluZyA/IDAgOiAxIH19PntjaGlsZHJlbn08L0lubmVyPlxyXG4gICAgICAgIDwvV3JhcHBlcj5cclxuICAgICAgPC9CdXR0b25CYXNlPlxyXG4gICAgKTtcclxuICB9LFxyXG4pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgQnV0dG9uO1xyXG5cclxuZXhwb3J0IGNvbnN0IFdyYXBwZXIgPSBzdHlsZWQuZGl2PFRCdXR0b25Qcm9wcz4oXHJcbiAge1xyXG4gICAgcG9zaXRpb246ICdyZWxhdGl2ZScsXHJcbiAgICBhcHBlYXJhbmNlOiAnbm9uZScsXHJcbiAgICBkaXNwbGF5OiAnaW5saW5lLWZsZXgnLFxyXG4gICAgYWxpZ25JdGVtczogJ2NlbnRlcicsXHJcbiAgICBib3JkZXI6IDAsXHJcbiAgICBvdXRsaW5lOiAwLFxyXG4gICAgYmFja2dyb3VuZENvbG9yOiAnaW5oZXJpdCcsXHJcbiAgICB0cmFuc2l0aW9uOiAnYWxsIDAuMTI1cyBlYXNlLW91dCcsXHJcbiAgICBjdXJzb3I6ICdwb2ludGVyJyxcclxuICAgIHBhZGRpbmc6ICcxLjByZW0gMi4ycmVtJyxcclxuICAgIGJvcmRlclJhZGl1czogJzAuNnJlbScsXHJcbiAgICBmb250U2l6ZTogJzEuNHJlbScsXHJcbiAgICAvLyAnJjpob3Zlcic6IHtcclxuICAgIC8vICAgdHJhbnNmb3JtOiAndHJhbnNsYXRlM2QoMCwgLTFweCwgMCknLFxyXG4gICAgLy8gfSxcclxuICAgIC8vICcmOmFjdGl2ZSc6IHtcclxuICAgIC8vICAgdHJhbnNmb3JtOiAndHJhbnNsYXRlM2QoMCwgMCwgMCknLFxyXG4gICAgLy8gfSxcclxuICB9LFxyXG4gICh7IHZhcmlhbnQsIGNvbG9yLCBzaXplLCBkaXNhYmxlZCwgZnVsbFdpZHRoIH0pID0+ICh7XHJcbiAgICAuLi4oc2l6ZSA9PT0gJ2luaGVyaXQnICYmIHtcclxuICAgICAgaGVpZ2h0OiAnYXV0bycsXHJcbiAgICAgIGxpbmVIZWlnaHQ6ICdpbmhlcml0JyxcclxuICAgICAgcGFkZGluZzogMCxcclxuICAgIH0pLFxyXG4gICAgLi4uKGZ1bGxXaWR0aCAmJiB7XHJcbiAgICAgIHdpZHRoOiAnMTAwJScsXHJcbiAgICB9KSxcclxuXHJcbiAgICAvLyAuLi5nZW5lcmF0ZUJ1dHRvblNpemVTdHlsZXMoeyBzaXplIH0pLFxyXG4gICAgLi4uZ2VuZXJhdGVCdXR0b25Db2xvclN0eWxlcyh7IHZhcmlhbnQsIGNvbG9yIH0pLFxyXG5cclxuICAgIC8vIGRpc2FibGVkXHJcbiAgICAnJltkaXNhYmxlZF0nOiB7XHJcbiAgICAgIC8vIOq4sOuzuFxyXG4gICAgICBvcGFjaXR5OiAwLjgsXHJcbiAgICAgIGN1cnNvcjogJ25vdC1hbGxvd2VkJyxcclxuXHJcbiAgICAgIC8vIHJlZCB0aGVtZVxyXG4gICAgICAnLnRoZW1lLWRhcmsgJic6IHtcclxuICAgICAgICBvcGFjaXR5OiAwLjUsXHJcbiAgICAgIH0sXHJcbiAgICB9LFxyXG4gIH0pLFxyXG4pO1xyXG5cclxuY29uc3QgSW5uZXIgPSBzdHlsZWQuZGl2YFxyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgbGluZS1oZWlnaHQ6IDE7XHJcbiAgY29sb3I6IGN1cnJlbnRDb2xvcjtcclxuICBmb250LXNpemU6IDFlbTtcclxuYDtcclxuIl19 */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});