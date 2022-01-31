import React from 'react';
import { CircularProgressProps } from '@mui/material/CircularProgress';
declare type TOverlaySpinnerProps = {
    backgroundColor?: React.CSSProperties['backgroundColor'];
    message?: string;
    WrapperProps?: Record<string, any>;
} & CircularProgressProps;
export default function OverlaySpinner({ backgroundColor, message, WrapperProps, ...rest }: TOverlaySpinnerProps): import("@emotion/react/jsx-runtime").JSX.Element;
export {};
