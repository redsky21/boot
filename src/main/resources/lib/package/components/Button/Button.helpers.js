var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.generateButtonSizeStyles = exports.generateButtonColorStyles = void 0;

var _colors = _interopRequireDefault(require("../../theme/colors"));

var generateButtonColorStyles = function generateButtonColorStyles(_ref) {
  var variant = _ref.variant,
      color = _ref.color;

  if (variant === 'contained') {
    switch (color) {
      case 'primary':
        {
          return {
            color: 'rgba(0,0,0,.9) !important',
            backgroundColor: _colors["default"]["secondary-500"],
            '&:hover': {
              backgroundColor: _colors["default"]["secondary-400"]
            },
            '&:active': {
              backgroundColor: _colors["default"]["secondary-300"]
            },
            '.theme-dark &': {
              color: 'hsla(0,0%,100%,.9) !important',
              backgroundColor: _colors["default"]["primary-500"],
              '&:hover': {
                backgroundColor: _colors["default"]["primary-400"]
              },
              '&:active': {
                backgroundColor: _colors["default"]["primary-300"]
              }
            }
          };
        }

      case 'primary-reverse':
        {
          return {
            color: _colors["default"]['primary-500'],
            backgroundColor: _colors["default"]['primary-100']
          };
        }

      case 'normal':
        {
          return {
            color: "".concat(_colors["default"]['gray-800'], " !important"),
            backgroundColor: _colors["default"]['gray-200'],
            '.theme-dark &': {
              color: 'hsla(0,0%,100%,.9) !important',
              backgroundColor: _colors["default"]['gray-500']
            }
          };
        }

      default:
        {
          return {};
        }
    }
  } else if (variant === 'outlined') {
    switch (color) {
      case 'primary':
        {
          return {
            color: _colors["default"]['primary-500'],
            border: '1px solid currentColor',
            '&:active': {
              opacity: 0.75
            }
          };
        }

      case 'primary-reverse':
        {
          return {
            color: _colors["default"]['primary-100'],
            border: '1px solid currentColor'
          };
        }

      case 'normal':
        {
          return {
            color: '#666',
            border: '1px solid currentColor',
            '&:active': {
              opacity: 0.75
            }
          };
        }

      default:
        {
          return {};
        }
    }
  } else {
    return {};
  }
};

exports.generateButtonColorStyles = generateButtonColorStyles;
var fontSize = {
  xs: 10,
  sm: 12,
  md: 14,
  lg: 16,
  xl: 18,
  inherit: 14
};

var generateButtonSizeStyles = function generateButtonSizeStyles(_ref2) {
  var _ref2$size = _ref2.size,
      size = _ref2$size === void 0 ? 'md' : _ref2$size;
  return {
    fontSize: fontSize[size],
    padding: size === 'inherit' ? '0' : '0.5em 0.9em'
  };
};

exports.generateButtonSizeStyles = generateButtonSizeStyles;