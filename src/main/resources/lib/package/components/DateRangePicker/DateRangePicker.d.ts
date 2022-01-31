import { DateRangePickerProps } from 'rsuite/DateRangePicker';
export declare type TDateRangePicker = {
    label?: string;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    required?: boolean;
    error?: boolean;
    WrapperProps?: Record<string, any>;
} & DateRangePickerProps;
declare function DateRangePicker({ label, labelAlign, labelWidth, required, error, WrapperProps, ...rest }: TDateRangePicker): import("@emotion/react/jsx-runtime").JSX.Element;
export default DateRangePicker;
