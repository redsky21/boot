var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _typeof = require("@babel/runtime/helpers/typeof");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Popover;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _slicedToArray2 = _interopRequireDefault(require("@babel/runtime/helpers/slicedToArray"));

var _react = _interopRequireWildcard(require("react"));

var _Popover = _interopRequireDefault(require("@mui/material/Popover"));

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function Popover(_ref) {
  var _ref$align = _ref.align,
      align = _ref$align === void 0 ? 'left' : _ref$align,
      renderTrigger = _ref.renderTrigger,
      children = _ref.children,
      _ref$MuiPopoverProps = _ref.MuiPopoverProps,
      MuiPopoverProps = _ref$MuiPopoverProps === void 0 ? {} : _ref$MuiPopoverProps;

  var _useState = (0, _react.useState)(null),
      _useState2 = (0, _slicedToArray2["default"])(_useState, 2),
      anchorEl = _useState2[0],
      setAnchorEl = _useState2[1];

  var handleClick = (0, _react.useCallback)(function (e) {
    return setAnchorEl(e.currentTarget);
  }, []);
  var handleClose = (0, _react.useCallback)(function () {
    return setAnchorEl(null);
  }, []);
  var TriggerEl = (0, _react.useMemo)(function () {
    return renderTrigger;
  }, []);
  return /*#__PURE__*/_react["default"].createElement(_react["default"].Fragment, null, /*#__PURE__*/_react["default"].createElement(TriggerEl, {
    onClick: handleClick
  }), /*#__PURE__*/_react["default"].createElement(_Popover["default"], (0, _extends2["default"])({
    anchorOrigin: {
      vertical: 'bottom',
      horizontal: align
    },
    transformOrigin: {
      vertical: 'top',
      horizontal: align
    }
  }, MuiPopoverProps, {
    anchorEl: anchorEl,
    open: Boolean(anchorEl),
    onClose: handleClose,
    sx: {
      '& .MuiPopover-paper': {
        marginTop: '1rem !important',
        borderRadius: '1rem !important',
        border: '1px solid rgba(0,0,0, 0.1) !important',
        boxShadow: '0 15px 20px rgba(0,0,0, 0.05) !important',
        backgroundColor: '#fff !important'
      }
    }
  }), children));
}