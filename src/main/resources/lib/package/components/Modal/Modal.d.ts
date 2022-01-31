import * as React from 'react';
import { ModalProps as MuiModalProps } from '@mui/material/Modal';
interface FadeProps {
    children?: React.ReactElement;
    in: boolean;
    onEnter?: () => {};
    onExited?: () => {};
    duration?: number;
}
declare type BaseModalProps = Partial<MuiModalProps>;
export declare type TModalProps = {
    isOpen: boolean;
    onClose: () => void;
    style?: React.CSSProperties;
    children: React.ReactNode;
    ModalProps?: BaseModalProps;
    FadeProps?: Omit<FadeProps, 'in'>;
};
export default function Modal({ isOpen, onClose, ModalProps, style, children, FadeProps, }: TModalProps): import("@emotion/react/jsx-runtime").JSX.Element;
export {};
