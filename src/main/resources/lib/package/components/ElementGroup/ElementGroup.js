var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = require("@emotion/react");

var _react2 = _interopRequireDefault(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _excluded = ["className"];
var StyledElementGroup = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "eqz8j2p0"
} : {
  target: "eqz8j2p0",
  label: "StyledElementGroup"
})("display:flex;align-items:flex-start;", function (_ref) {
  var _ref$direction = _ref.direction,
      direction = _ref$direction === void 0 ? 'row' : _ref$direction,
      _ref$gap = _ref.gap,
      gap = _ref$gap === void 0 ? '1rem' : _ref$gap,
      _ref$isAlignedRight = _ref.isAlignedRight,
      isAlignedRight = _ref$isAlignedRight === void 0 ? false : _ref$isAlignedRight,
      _ref$alignItems = _ref.alignItems,
      alignItems = _ref$alignItems === void 0 ? 'flex-start' : _ref$alignItems,
      _ref$justifyContent = _ref.justifyContent,
      justifyContent = _ref$justifyContent === void 0 ? 'flex-start' : _ref$justifyContent,
      _ref$flexWrap = _ref.flexWrap,
      flexWrap = _ref$flexWrap === void 0 ? 'nowrap' : _ref$flexWrap,
      _ref$fullWidth = _ref.fullWidth,
      fullWidth = _ref$fullWidth === void 0 ? false : _ref$fullWidth;
  var marginType = direction === 'row' ? 'marginLeft' : 'marginTop';
  return /*#__PURE__*/(0, _react.css)({
    width: fullWidth ? '100%' : 'auto',
    flexDirection: direction,
    flexWrap: flexWrap,
    alignItems: alignItems,
    justifyContent: isAlignedRight ? 'flex-end' : justifyContent,
    '& > * + *': (0, _defineProperty2["default"])({}, marginType, "".concat(typeof gap === 'number' ? "".concat(gap, "px") : gap, " !important"))
  }, process.env.NODE_ENV === "production" ? "" : ";label:StyledElementGroup;", process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0VsZW1lbnRHcm91cC9FbGVtZW50R3JvdXAudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQTZCVyIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9FbGVtZW50R3JvdXAvRWxlbWVudEdyb3VwLnRzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IGNzcyB9IGZyb20gJ0BlbW90aW9uL3JlYWN0JztcclxuaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgY24gZnJvbSAnY2xhc3NuYW1lcyc7XHJcblxyXG5leHBvcnQgdHlwZSBURWxlbWVudEdyb3VwUHJvcHMgPSB7XHJcbiAgZGlyZWN0aW9uPzogJ3JvdycgfCAnY29sdW1uJzsgLy8g67Cp7ZalXHJcbiAgaXNBbGlnbmVkUmlnaHQ/OiBib29sZWFuOyAvLyDsmrDsuKEg7KCV66CsXHJcbiAgZnVsbFdpZHRoPzogYm9vbGVhbjtcclxuICBnYXA/OiBudW1iZXIgfCBzdHJpbmc7IC8vIOyekOyLnSDsmpTshozqsIQg6rCE6rKpXHJcbiAgY2hpbGRyZW4/OiBSZWFjdC5SZWFjdE5vZGU7XHJcbiAganVzdGlmeUNvbnRlbnQ/OiBSZWFjdC5DU1NQcm9wZXJ0aWVzWydqdXN0aWZ5Q29udGVudCddO1xyXG4gIGFsaWduSXRlbXM/OiBSZWFjdC5DU1NQcm9wZXJ0aWVzWydhbGlnbkl0ZW1zJ107XHJcbiAgZmxleFdyYXA/OiBSZWFjdC5DU1NQcm9wZXJ0aWVzWydmbGV4V3JhcCddO1xyXG59ICYgUmVhY3QuSFRNTEF0dHJpYnV0ZXM8SFRNTERpdkVsZW1lbnQ+O1xyXG5cclxuY29uc3QgU3R5bGVkRWxlbWVudEdyb3VwID0gc3R5bGVkLmRpdjxURWxlbWVudEdyb3VwUHJvcHM+YFxyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgYWxpZ24taXRlbXM6IGZsZXgtc3RhcnQ7XHJcbiAgJHsoe1xyXG4gICAgZGlyZWN0aW9uID0gJ3JvdycsXHJcbiAgICBnYXAgPSAnMXJlbScsXHJcbiAgICBpc0FsaWduZWRSaWdodCA9IGZhbHNlLFxyXG4gICAgYWxpZ25JdGVtcyA9ICdmbGV4LXN0YXJ0JyxcclxuICAgIGp1c3RpZnlDb250ZW50ID0gJ2ZsZXgtc3RhcnQnLFxyXG4gICAgZmxleFdyYXAgPSAnbm93cmFwJyxcclxuICAgIGZ1bGxXaWR0aCA9IGZhbHNlLFxyXG4gIH0pID0+IHtcclxuICAgIGNvbnN0IG1hcmdpblR5cGUgPSBkaXJlY3Rpb24gPT09ICdyb3cnID8gJ21hcmdpbkxlZnQnIDogJ21hcmdpblRvcCc7XHJcbiAgICByZXR1cm4gY3NzKHtcclxuICAgICAgd2lkdGg6IGZ1bGxXaWR0aCA/ICcxMDAlJyA6ICdhdXRvJyxcclxuICAgICAgZmxleERpcmVjdGlvbjogZGlyZWN0aW9uLFxyXG4gICAgICBmbGV4V3JhcCxcclxuICAgICAgYWxpZ25JdGVtcyxcclxuICAgICAganVzdGlmeUNvbnRlbnQ6IGlzQWxpZ25lZFJpZ2h0ID8gJ2ZsZXgtZW5kJyA6IGp1c3RpZnlDb250ZW50LFxyXG4gICAgICAnJiA+ICogKyAqJzoge1xyXG4gICAgICAgIFttYXJnaW5UeXBlXTogYCR7dHlwZW9mIGdhcCA9PT0gJ251bWJlcicgPyBgJHtnYXB9cHhgIDogZ2FwfSAhaW1wb3J0YW50YCxcclxuICAgICAgfSxcclxuICAgIH0pO1xyXG4gIH19XHJcbmA7XHJcblxyXG5jb25zdCBFbGVtZW50R3JvdXAgPSBSZWFjdC5mb3J3YXJkUmVmPEhUTUxEaXZFbGVtZW50LCBURWxlbWVudEdyb3VwUHJvcHM+KChwcm9wcywgcmVmKSA9PiB7XHJcbiAgY29uc3QgeyBjbGFzc05hbWUsIC4uLnJlc3QgfSA9IHByb3BzO1xyXG5cclxuICByZXR1cm4gKFxyXG4gICAgPFN0eWxlZEVsZW1lbnRHcm91cFxyXG4gICAgICBjbGFzc05hbWU9e2NuKCdjbnN1aS1lbGVtZW50Z3JvdXAnLCBjbGFzc05hbWUpfVxyXG4gICAgICByZWY9e3JlZn1cclxuICAgICAgey4uLnJlc3R9XHJcbiAgICA+PC9TdHlsZWRFbGVtZW50R3JvdXA+XHJcbiAgKTtcclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBFbGVtZW50R3JvdXA7XHJcbiJdfQ== */");
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0VsZW1lbnRHcm91cC9FbGVtZW50R3JvdXAudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQWdCeUQiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvRWxlbWVudEdyb3VwL0VsZW1lbnRHcm91cC50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBjc3MgfSBmcm9tICdAZW1vdGlvbi9yZWFjdCc7XHJcbmltcG9ydCBzdHlsZWQgZnJvbSAnQGVtb3Rpb24vc3R5bGVkJztcclxuaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IGNuIGZyb20gJ2NsYXNzbmFtZXMnO1xyXG5cclxuZXhwb3J0IHR5cGUgVEVsZW1lbnRHcm91cFByb3BzID0ge1xyXG4gIGRpcmVjdGlvbj86ICdyb3cnIHwgJ2NvbHVtbic7IC8vIOuwqe2WpVxyXG4gIGlzQWxpZ25lZFJpZ2h0PzogYm9vbGVhbjsgLy8g7Jqw7LihIOygleugrFxyXG4gIGZ1bGxXaWR0aD86IGJvb2xlYW47XHJcbiAgZ2FwPzogbnVtYmVyIHwgc3RyaW5nOyAvLyDsnpDsi50g7JqU7IaM6rCEIOqwhOqyqVxyXG4gIGNoaWxkcmVuPzogUmVhY3QuUmVhY3ROb2RlO1xyXG4gIGp1c3RpZnlDb250ZW50PzogUmVhY3QuQ1NTUHJvcGVydGllc1snanVzdGlmeUNvbnRlbnQnXTtcclxuICBhbGlnbkl0ZW1zPzogUmVhY3QuQ1NTUHJvcGVydGllc1snYWxpZ25JdGVtcyddO1xyXG4gIGZsZXhXcmFwPzogUmVhY3QuQ1NTUHJvcGVydGllc1snZmxleFdyYXAnXTtcclxufSAmIFJlYWN0LkhUTUxBdHRyaWJ1dGVzPEhUTUxEaXZFbGVtZW50PjtcclxuXHJcbmNvbnN0IFN0eWxlZEVsZW1lbnRHcm91cCA9IHN0eWxlZC5kaXY8VEVsZW1lbnRHcm91cFByb3BzPmBcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGFsaWduLWl0ZW1zOiBmbGV4LXN0YXJ0O1xyXG4gICR7KHtcclxuICAgIGRpcmVjdGlvbiA9ICdyb3cnLFxyXG4gICAgZ2FwID0gJzFyZW0nLFxyXG4gICAgaXNBbGlnbmVkUmlnaHQgPSBmYWxzZSxcclxuICAgIGFsaWduSXRlbXMgPSAnZmxleC1zdGFydCcsXHJcbiAgICBqdXN0aWZ5Q29udGVudCA9ICdmbGV4LXN0YXJ0JyxcclxuICAgIGZsZXhXcmFwID0gJ25vd3JhcCcsXHJcbiAgICBmdWxsV2lkdGggPSBmYWxzZSxcclxuICB9KSA9PiB7XHJcbiAgICBjb25zdCBtYXJnaW5UeXBlID0gZGlyZWN0aW9uID09PSAncm93JyA/ICdtYXJnaW5MZWZ0JyA6ICdtYXJnaW5Ub3AnO1xyXG4gICAgcmV0dXJuIGNzcyh7XHJcbiAgICAgIHdpZHRoOiBmdWxsV2lkdGggPyAnMTAwJScgOiAnYXV0bycsXHJcbiAgICAgIGZsZXhEaXJlY3Rpb246IGRpcmVjdGlvbixcclxuICAgICAgZmxleFdyYXAsXHJcbiAgICAgIGFsaWduSXRlbXMsXHJcbiAgICAgIGp1c3RpZnlDb250ZW50OiBpc0FsaWduZWRSaWdodCA/ICdmbGV4LWVuZCcgOiBqdXN0aWZ5Q29udGVudCxcclxuICAgICAgJyYgPiAqICsgKic6IHtcclxuICAgICAgICBbbWFyZ2luVHlwZV06IGAke3R5cGVvZiBnYXAgPT09ICdudW1iZXInID8gYCR7Z2FwfXB4YCA6IGdhcH0gIWltcG9ydGFudGAsXHJcbiAgICAgIH0sXHJcbiAgICB9KTtcclxuICB9fVxyXG5gO1xyXG5cclxuY29uc3QgRWxlbWVudEdyb3VwID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVEVsZW1lbnRHcm91cFByb3BzPigocHJvcHMsIHJlZikgPT4ge1xyXG4gIGNvbnN0IHsgY2xhc3NOYW1lLCAuLi5yZXN0IH0gPSBwcm9wcztcclxuXHJcbiAgcmV0dXJuIChcclxuICAgIDxTdHlsZWRFbGVtZW50R3JvdXBcclxuICAgICAgY2xhc3NOYW1lPXtjbignY25zdWktZWxlbWVudGdyb3VwJywgY2xhc3NOYW1lKX1cclxuICAgICAgcmVmPXtyZWZ9XHJcbiAgICAgIHsuLi5yZXN0fVxyXG4gICAgPjwvU3R5bGVkRWxlbWVudEdyb3VwPlxyXG4gICk7XHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgRWxlbWVudEdyb3VwO1xyXG4iXX0= */"));

var ElementGroup = /*#__PURE__*/_react2["default"].forwardRef(function (props, ref) {
  var className = props.className,
      rest = (0, _objectWithoutProperties2["default"])(props, _excluded);
  return /*#__PURE__*/_react2["default"].createElement(StyledElementGroup, (0, _extends2["default"])({
    className: (0, _classnames["default"])('cnsui-elementgroup', className),
    ref: ref
  }, rest));
});

var _default = ElementGroup;
exports["default"] = _default;