var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = TabItem;

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _Tab = require("./Tab.context");

var _excluded = ["label", "value", "disabled", "children"];

function TabItem(_ref) {
  var label = _ref.label,
      value = _ref.value,
      _ref$disabled = _ref.disabled,
      disabled = _ref$disabled === void 0 ? false : _ref$disabled,
      children = _ref.children,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);

  var _useTabContext = (0, _Tab.useTabContext)(),
      register = _useTabContext.register,
      currentValue = _useTabContext.currentValue;

  _react["default"].useEffect(function () {
    register({
      value: value,
      label: label,
      disabled: disabled
    });
  }, []);

  var isActive = _react["default"].useMemo(function () {
    return currentValue === value;
  }, [currentValue, value]);

  return isActive ? /*#__PURE__*/_react["default"].createElement(_react["default"].Fragment, null, children) : null;
}