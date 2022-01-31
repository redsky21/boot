var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _RadioGroupContext = _interopRequireDefault(require("./RadioGroupContext"));

var _react2 = require("@emotion/react");

var _InputWrapper = _interopRequireDefault(require("../InputWrapper"));

var _excluded = ["gap", "direction", "name", "value", "label", "required", "error", "labelWidth", "labelAlign", "onChange", "children", "WrapperProps"];

var RadioGroup = /*#__PURE__*/_react["default"].forwardRef(function (_ref, ref) {
  var _ref$gap = _ref.gap,
      gap = _ref$gap === void 0 ? 10 : _ref$gap,
      _ref$direction = _ref.direction,
      direction = _ref$direction === void 0 ? 'row' : _ref$direction,
      name = _ref.name,
      value = _ref.value,
      label = _ref.label,
      required = _ref.required,
      error = _ref.error,
      labelWidth = _ref.labelWidth,
      labelAlign = _ref.labelAlign,
      onChange = _ref.onChange,
      children = _ref.children,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_RadioGroupContext["default"].Provider, {
    value: {
      name: name,
      value: value,
      onChange: onChange
    }
  }, /*#__PURE__*/_react["default"].createElement(_InputWrapper["default"], (0, _extends2["default"])({
    label: label,
    required: required,
    error: error,
    labelWidth: labelWidth,
    labelAlign: labelAlign
  }, WrapperProps), /*#__PURE__*/_react["default"].createElement(Wrapper, (0, _extends2["default"])({
    ref: ref,
    direction: direction,
    gap: gap
  }, rest), children)));
});

var _default = RadioGroup;
exports["default"] = _default;
var Wrapper = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e3b5m2k0"
} : {
  target: "e3b5m2k0",
  label: "Wrapper"
})("display:flex;flex-wrap:wrap;", function (_ref2) {
  var direction = _ref2.direction,
      gap = _ref2.gap;
  return /*#__PURE__*/(0, _react2.css)("flex-direction:", direction || 'column', ";&>*+*{", direction === 'column' ? 'margin-top' : 'margin-left', ":", typeof gap === 'number' ? "".concat(gap, "px") : gap, "!important;}" + (process.env.NODE_ENV === "production" ? "" : ";label:Wrapper;"), process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1JhZGlvR3JvdXAvUmFkaW9Hcm91cC50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBaUQrQiIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9SYWRpb0dyb3VwL1JhZGlvR3JvdXAudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJhZGlvR3JvdXBDb250ZXh0IGZyb20gJy4vUmFkaW9Hcm91cENvbnRleHQnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcblxyXG5pbXBvcnQgeyBUUmFkaW9Hcm91cFByb3BzIH0gZnJvbSAnLi9SYWRpb0dyb3VwLnR5cGVzJztcclxuaW1wb3J0IHsgY3NzIH0gZnJvbSAnQGVtb3Rpb24vcmVhY3QnO1xyXG5pbXBvcnQgSW5wdXRXcmFwcGVyIGZyb20gJy4uL0lucHV0V3JhcHBlcic7XHJcblxyXG5jb25zdCBSYWRpb0dyb3VwID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVFJhZGlvR3JvdXBQcm9wcz4oXHJcbiAgKFxyXG4gICAge1xyXG4gICAgICBnYXAgPSAxMCxcclxuICAgICAgZGlyZWN0aW9uID0gJ3JvdycsXHJcbiAgICAgIG5hbWUsXHJcbiAgICAgIHZhbHVlLFxyXG4gICAgICBsYWJlbCxcclxuICAgICAgcmVxdWlyZWQsXHJcbiAgICAgIGVycm9yLFxyXG4gICAgICBsYWJlbFdpZHRoLFxyXG4gICAgICBsYWJlbEFsaWduLFxyXG4gICAgICBvbkNoYW5nZSxcclxuICAgICAgY2hpbGRyZW4sXHJcbiAgICAgIFdyYXBwZXJQcm9wcyA9IHt9LFxyXG4gICAgICAuLi5yZXN0XHJcbiAgICB9LFxyXG4gICAgcmVmLFxyXG4gICkgPT4gKFxyXG4gICAgPFJhZGlvR3JvdXBDb250ZXh0LlByb3ZpZGVyIHZhbHVlPXt7IG5hbWUsIHZhbHVlLCBvbkNoYW5nZSB9fT5cclxuICAgICAgPElucHV0V3JhcHBlclxyXG4gICAgICAgIGxhYmVsPXtsYWJlbH1cclxuICAgICAgICByZXF1aXJlZD17cmVxdWlyZWR9XHJcbiAgICAgICAgZXJyb3I9e2Vycm9yfVxyXG4gICAgICAgIGxhYmVsV2lkdGg9e2xhYmVsV2lkdGh9XHJcbiAgICAgICAgbGFiZWxBbGlnbj17bGFiZWxBbGlnbn1cclxuICAgICAgICB7Li4uV3JhcHBlclByb3BzfVxyXG4gICAgICA+XHJcbiAgICAgICAgPFdyYXBwZXIgcmVmPXtyZWZ9IGRpcmVjdGlvbj17ZGlyZWN0aW9ufSBnYXA9e2dhcH0gey4uLnJlc3R9PlxyXG4gICAgICAgICAge2NoaWxkcmVufVxyXG4gICAgICAgIDwvV3JhcHBlcj5cclxuICAgICAgPC9JbnB1dFdyYXBwZXI+XHJcbiAgICA8L1JhZGlvR3JvdXBDb250ZXh0LlByb3ZpZGVyPlxyXG4gICksXHJcbik7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBSYWRpb0dyb3VwO1xyXG5cclxuY29uc3QgV3JhcHBlciA9IHN0eWxlZC5kaXY8UGljazxUUmFkaW9Hcm91cFByb3BzLCAnZGlyZWN0aW9uJyB8ICdnYXAnPj5gXHJcbiAgZGlzcGxheTogZmxleDtcclxuICBmbGV4LXdyYXA6IHdyYXA7XHJcbiAgJHsoeyBkaXJlY3Rpb24sIGdhcCB9KSA9PiBjc3NgXHJcbiAgICBmbGV4LWRpcmVjdGlvbjogJHtkaXJlY3Rpb24gfHwgJ2NvbHVtbid9O1xyXG4gICAgJiA+ICogKyAqIHtcclxuICAgICAgJHtkaXJlY3Rpb24gPT09ICdjb2x1bW4nID8gJ21hcmdpbi10b3AnIDogJ21hcmdpbi1sZWZ0J306ICR7dHlwZW9mIGdhcCA9PT0gJ251bWJlcidcclxuICAgICAgICA/IGAke2dhcH1weGBcclxuICAgICAgICA6IGdhcH0gIWltcG9ydGFudDtcclxuICAgIH1cclxuICBgfVxyXG5gO1xyXG4iXX0= */");
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1JhZGlvR3JvdXAvUmFkaW9Hcm91cC50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBOEN1RSIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9SYWRpb0dyb3VwL1JhZGlvR3JvdXAudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJhZGlvR3JvdXBDb250ZXh0IGZyb20gJy4vUmFkaW9Hcm91cENvbnRleHQnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcblxyXG5pbXBvcnQgeyBUUmFkaW9Hcm91cFByb3BzIH0gZnJvbSAnLi9SYWRpb0dyb3VwLnR5cGVzJztcclxuaW1wb3J0IHsgY3NzIH0gZnJvbSAnQGVtb3Rpb24vcmVhY3QnO1xyXG5pbXBvcnQgSW5wdXRXcmFwcGVyIGZyb20gJy4uL0lucHV0V3JhcHBlcic7XHJcblxyXG5jb25zdCBSYWRpb0dyb3VwID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVFJhZGlvR3JvdXBQcm9wcz4oXHJcbiAgKFxyXG4gICAge1xyXG4gICAgICBnYXAgPSAxMCxcclxuICAgICAgZGlyZWN0aW9uID0gJ3JvdycsXHJcbiAgICAgIG5hbWUsXHJcbiAgICAgIHZhbHVlLFxyXG4gICAgICBsYWJlbCxcclxuICAgICAgcmVxdWlyZWQsXHJcbiAgICAgIGVycm9yLFxyXG4gICAgICBsYWJlbFdpZHRoLFxyXG4gICAgICBsYWJlbEFsaWduLFxyXG4gICAgICBvbkNoYW5nZSxcclxuICAgICAgY2hpbGRyZW4sXHJcbiAgICAgIFdyYXBwZXJQcm9wcyA9IHt9LFxyXG4gICAgICAuLi5yZXN0XHJcbiAgICB9LFxyXG4gICAgcmVmLFxyXG4gICkgPT4gKFxyXG4gICAgPFJhZGlvR3JvdXBDb250ZXh0LlByb3ZpZGVyIHZhbHVlPXt7IG5hbWUsIHZhbHVlLCBvbkNoYW5nZSB9fT5cclxuICAgICAgPElucHV0V3JhcHBlclxyXG4gICAgICAgIGxhYmVsPXtsYWJlbH1cclxuICAgICAgICByZXF1aXJlZD17cmVxdWlyZWR9XHJcbiAgICAgICAgZXJyb3I9e2Vycm9yfVxyXG4gICAgICAgIGxhYmVsV2lkdGg9e2xhYmVsV2lkdGh9XHJcbiAgICAgICAgbGFiZWxBbGlnbj17bGFiZWxBbGlnbn1cclxuICAgICAgICB7Li4uV3JhcHBlclByb3BzfVxyXG4gICAgICA+XHJcbiAgICAgICAgPFdyYXBwZXIgcmVmPXtyZWZ9IGRpcmVjdGlvbj17ZGlyZWN0aW9ufSBnYXA9e2dhcH0gey4uLnJlc3R9PlxyXG4gICAgICAgICAge2NoaWxkcmVufVxyXG4gICAgICAgIDwvV3JhcHBlcj5cclxuICAgICAgPC9JbnB1dFdyYXBwZXI+XHJcbiAgICA8L1JhZGlvR3JvdXBDb250ZXh0LlByb3ZpZGVyPlxyXG4gICksXHJcbik7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBSYWRpb0dyb3VwO1xyXG5cclxuY29uc3QgV3JhcHBlciA9IHN0eWxlZC5kaXY8UGljazxUUmFkaW9Hcm91cFByb3BzLCAnZGlyZWN0aW9uJyB8ICdnYXAnPj5gXHJcbiAgZGlzcGxheTogZmxleDtcclxuICBmbGV4LXdyYXA6IHdyYXA7XHJcbiAgJHsoeyBkaXJlY3Rpb24sIGdhcCB9KSA9PiBjc3NgXHJcbiAgICBmbGV4LWRpcmVjdGlvbjogJHtkaXJlY3Rpb24gfHwgJ2NvbHVtbid9O1xyXG4gICAgJiA+ICogKyAqIHtcclxuICAgICAgJHtkaXJlY3Rpb24gPT09ICdjb2x1bW4nID8gJ21hcmdpbi10b3AnIDogJ21hcmdpbi1sZWZ0J306ICR7dHlwZW9mIGdhcCA9PT0gJ251bWJlcidcclxuICAgICAgICA/IGAke2dhcH1weGBcclxuICAgICAgICA6IGdhcH0gIWltcG9ydGFudDtcclxuICAgIH1cclxuICBgfVxyXG5gO1xyXG4iXX0= */"));