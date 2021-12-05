//
//  PullToRefresh.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PullToRefreshView: UIViewRepresentable {
    
    typealias UIViewType = UIView
    
    let action: () -> Void
    @Binding var isShowing: Bool
    
    public init(
        action: @escaping () -> Void,
        isShowing: Binding<Bool>
    ) {
        self.action = action
        _isShowing = isShowing
    }
    
    func makeUIView(context: Context) -> UIView {
        return UIView(frame: .zero)
    }
    
    func updateUIView(_ uiView: UIView, context: Context) {
        DispatchQueue.main.asyncAfter(deadline: .now()) {
            guard let viewHost = uiView.superview?.superview else {
                return
            }
            guard let tableView = self.tableView(root: viewHost) else {
                return
            }
            
            if let refreshControl = tableView.refreshControl {
                if self.isShowing {
                    refreshControl.beginRefreshing()
                } else {
                    refreshControl.endRefreshing()
                }
                return
            }
            
            let refreshControl = UIRefreshControl()
            refreshControl.addTarget(context.coordinator, action: #selector(Coordinator.onValueChanged), for: .valueChanged)
            tableView.refreshControl = refreshControl
        }
    }
    
    func makeCoordinator() -> Coordinator {
        return Coordinator(action: action, isShowing: $isShowing)
    }
    
    private func tableView(root: UIView) -> UITableView? {
        for subview in root.subviews {
            if let tableView = subview as? UITableView {
                return tableView
            } else if let tableView = tableView(root: subview) {
                return tableView
            }
        }
        return nil
    }
    
    class Coordinator {
        let action: () -> Void
        let isShowing: Binding<Bool>
        
        init(
            action: @escaping () -> Void,
            isShowing: Binding<Bool>
        ) {
            self.action = action
            self.isShowing = isShowing
        }
        
        @objc
        func onValueChanged() {
            isShowing.wrappedValue = true
            action()
        }
    }
}


extension View {
    public func pullToRefresh(isShowing: Binding<Bool>, onRefresh: @escaping () -> Void) -> some View {
        return overlay(
            PullToRefreshView(action: onRefresh, isShowing: isShowing)
                .frame(width: 0, height: 0)
        )
    }
}
