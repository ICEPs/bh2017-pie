class UsersController < ApplicationController
  before_action :set_user, only: [:show, :update, :destroy]

  def index
    @users = User.all

    render json: @users
  end

  def create
    @user = User.new
    @user.company_name = params[:company_name]
    @user.email = params[:email]
    @user.password = params[:password]
    @user.address = params[:address]
    @user.rep_name = params[:rep_name]
    @user.rep_contact_info = params[:rep_contact_info]
    @user.company_description = params[:company_description]
    @user.user_type = params[:user_type]

    if @user.save
      render json: @user, status: :created, location: @user
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def show
    render json: @users
  end

  def update
    @user = User.find(params[:id])

    if @user.update(item_params)
      head :no_content
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @user.destroy

    head :no_content
  end



  private

    def set_user
      @user = User.find(params[:id])
    end

    def item_params
      params.require(:user).permit!
    end

end
