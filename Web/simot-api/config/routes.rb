Rails.application.routes.draw do

  resources :users
  resources :items
  resources :donations
  resources :transactions
  post 'authenticate', to: 'authentication#authenticate'

end
