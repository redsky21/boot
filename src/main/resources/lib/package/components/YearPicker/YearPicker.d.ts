import { DatePickerProps } from '@mui/lab/DatePicker';
declare type __Props = {
    label?: string;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    required?: boolean;
    error?: boolean;
    WrapperProps?: Record<string, any>;
    disableMonthPicker?: boolean;
};
export declare type TYearPickerProps = __Props & Omit<DatePickerProps, keyof __Props | 'renderInput' | 'views'>;
declare function YearPicker({ value, onChange, label, labelAlign, labelWidth, required, error, disableMonthPicker, WrapperProps, ...rest }: TYearPickerProps): import("@emotion/react/jsx-runtime").JSX.Element;
export default YearPicker;
