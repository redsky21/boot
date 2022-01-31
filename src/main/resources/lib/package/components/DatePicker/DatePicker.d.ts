import React, { CSSProperties } from 'react';
import { DatePickerProps } from 'rsuite/DatePicker';
export declare type TDatePicker = {
    label?: string;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    required?: boolean;
    error?: boolean;
    width?: CSSProperties['width'];
    fullWidth?: boolean;
    WrapperProps?: Record<string, any>;
    disablePortal?: boolean;
} & DatePickerProps;
/** https://rsuitejs.com/components/date-picker/#%3CDatePicker%3E */
declare const DatePicker: React.ForwardRefExoticComponent<{
    label?: string | undefined;
    labelAlign?: "left" | "right" | undefined;
    labelWidth?: string | number | undefined;
    required?: boolean | undefined;
    error?: boolean | undefined;
    width?: CSSProperties['width'];
    fullWidth?: boolean | undefined;
    WrapperProps?: Record<string, any> | undefined;
    disablePortal?: boolean | undefined;
} & DatePickerProps & React.RefAttributes<HTMLDivElement>>;
export default DatePicker;
