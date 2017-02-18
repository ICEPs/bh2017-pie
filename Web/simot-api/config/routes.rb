Rails.application.routes.draw do

  resources :users
  resources :items
  resources :donations
  post 'authenticate', to: 'authentication#authenticate'

end
