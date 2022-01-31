import { TButtonProps } from './Button';
export declare const generateButtonColorStyles: ({ variant, color, }: Pick<TButtonProps, 'variant' | 'color'>) => {
    color: string;
    backgroundColor: string;
    '&:hover': {
        backgroundColor: string;
    };
    '&:active': {
        backgroundColor: string;
        opacity?: undefined;
    };
    '.theme-dark &': {
        color: string;
        backgroundColor: string;
        '&:hover': {
            backgroundColor: string;
        };
        '&:active': {
            backgroundColor: string;
        };
    };
    border?: undefined;
} | {
    color: string;
    backgroundColor: string;
    '&:hover'?: undefined;
    '&:active'?: undefined;
    '.theme-dark &'?: undefined;
    border?: undefined;
} | {
    color: string;
    backgroundColor: string;
    '.theme-dark &': {
        color: string;
        backgroundColor: string;
        '&:hover'?: undefined;
        '&:active'?: undefined;
    };
    '&:hover'?: undefined;
    '&:active'?: undefined;
    border?: undefined;
} | {
    color?: undefined;
    backgroundColor?: undefined;
    '&:hover'?: undefined;
    '&:active'?: undefined;
    '.theme-dark &'?: undefined;
    border?: undefined;
} | {
    color: string;
    border: string;
    '&:active': {
        opacity: number;
        backgroundColor?: undefined;
    };
    backgroundColor?: undefined;
    '&:hover'?: undefined;
    '.theme-dark &'?: undefined;
} | {
    color: string;
    border: string;
    backgroundColor?: undefined;
    '&:hover'?: undefined;
    '&:active'?: undefined;
    '.theme-dark &'?: undefined;
};
export declare const generateButtonSizeStyles: ({ size }: Pick<TButtonProps, 'size'>) => {
    fontSize: number;
    padding: string;
};
