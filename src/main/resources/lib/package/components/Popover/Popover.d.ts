import React from 'react';
import { PopoverProps as MuiPopoverProps } from '@mui/material/Popover';
export declare type TPopoverProps = {
    align: 'left' | 'right' | 'center';
    renderTrigger: React.FunctionComponent<{
        onClick: (e: React.MouseEvent<HTMLElement>) => void;
    }>;
    children?: React.ReactNode;
    MuiPopoverProps?: Omit<MuiPopoverProps, 'open' | 'anchorEl'>;
};
export default function Popover({ align, renderTrigger, children, MuiPopoverProps, }: TPopoverProps): import("@emotion/react/jsx-runtime").JSX.Element;
